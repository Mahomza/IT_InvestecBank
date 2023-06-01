package json;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

/**
 * <p>Title: JSONMapper </p>
 *
 * <p>Description: Json mapper </p>
 *
 * <p>Copyright: Copyright (c) 2023</p>
 *
 * <p>Company: Investec_Bank</p>
 *
 * @author Alex Mahomana
 * @version 1.0
 */
public class JSONMapper
{
  private static final String EXCEPTION_MESSAGE_SOMETHING_WENT_WRONG = "Something went wrong";
  private int currentIndentCnt;

  public String toJSON(JSONHolder genericJSONHolder)
  {
    if (genericJSONHolder != null)
    {
      currentIndentCnt = 0;
      StringBuilder jsonBuilder = new StringBuilder();

      handleContent(genericJSONHolder.getDataMap(), jsonBuilder);
      return jsonBuilder.toString();
    }
    return null;
  }

  private void newLineAndIndent(StringBuilder jsonBuilder)
  {
    jsonBuilder.append('\n');
    IntStream.range(0, currentIndentCnt).forEach(i -> jsonBuilder.append('\t'));
  }

  private void handleContent(Map<String, JSONHolder> dataMap, StringBuilder jsonBuilder)
  {
    jsonBuilder.append('{');
    currentIndentCnt++;
    newLineAndIndent(jsonBuilder);

    int cnt = 0;
    for (Entry<String, JSONHolder> entry : dataMap.entrySet())
    {
      cnt++;
      JSONHolder genericJSONHolder = entry.getValue();
      String key = entry.getKey();

      jsonBuilder.append('"');
      jsonBuilder.append(key);
      jsonBuilder.append('"');
      jsonBuilder.append(": ");

      if (genericJSONHolder.isValue())
      {
        jsonBuilder.append('"');
        jsonBuilder.append(genericJSONHolder.getValue());
        jsonBuilder.append('"');
      }
      else
      {
        newLineAndIndent(jsonBuilder);
        handleContent(genericJSONHolder.getDataMap(), jsonBuilder);
      }

      if (cnt < dataMap.size())
      {
        jsonBuilder.append(',');
        newLineAndIndent(jsonBuilder);
      }
    }
    currentIndentCnt--;
    newLineAndIndent(jsonBuilder);
    jsonBuilder.append('}');

  }

  public List<JSONHolder> parseJSONStr(String json)
  {
    if (json != null && !json.isEmpty())
    {
      List<JSONHolder> root = new ArrayList<>();
      int dataLevel = 0;
      Map<Integer, String> dataLevelIdentifier = new HashMap<>();
      Map<Integer, Map<String, JSONHolder>> mapPerLevel = new HashMap<>();
      //identifiers
      boolean identifierInitialised = false;
      String currentIdentifier = null;
      boolean valueInitialised = false;
      StringBuilder builder = null;

      for (int x = 0; x < json.length(); x++)
      {
        //start new level
        if (json.charAt(x) == '{')
        {
          if (dataLevel > 0)
          {
            dataLevelIdentifier.put(dataLevel + 1, currentIdentifier);
            currentIdentifier = null;
          }
          dataLevel++;

          mapPerLevel.put(dataLevel, new HashMap<>());
        }

        //start / end identifier or value
        else if (json.charAt(x) == '"')
        {
          if (valueInitialised)
          {
            if (builder != null && mapPerLevel.get(dataLevel) != null)
            {
              String value = builder.toString();
              JSONHolder genericJSONHolder = new JSONHolder(value);
              mapPerLevel.get(dataLevel).put(currentIdentifier, genericJSONHolder);

              currentIdentifier = null;
              valueInitialised = false;
            }
            else
            {
              throw new IllegalStateException(EXCEPTION_MESSAGE_SOMETHING_WENT_WRONG);
            }
          }
          else if (identifierInitialised)
          {
            if (builder != null)
            {
              currentIdentifier = builder.toString();
              identifierInitialised = false;
            }
            else
            {
              throw new IllegalStateException(EXCEPTION_MESSAGE_SOMETHING_WENT_WRONG);
            }
          }
          else if (!identifierInitialised && currentIdentifier == null)
          {
            //init the identifier
            identifierInitialised = true;
            builder = new StringBuilder();
          }
          else if (!valueInitialised)
          {
            //init the current value
            valueInitialised = true;
            builder = new StringBuilder();
          }

        }
        //signifies end of identifier, do some validations
        else if (json.charAt(x) == ':')
        {
          if (identifierInitialised || currentIdentifier == null)
          {
            throw new IllegalStateException("Something went wrong, the : seems to be missing");
          }
        }
        //validate array structure
        else if (json.charAt(x) == '[' || json.charAt(x) == ']')
        {
          if (!identifierInitialised && !valueInitialised)
          {
            throw new IllegalStateException("Sorry arrays are not supported by this parser (yet)");
          }
        }
        //close bracket so assemble level
        else if (json.charAt(x) == '}')
        {
          JSONHolder genericJSONHolder = new JSONHolder(mapPerLevel.get(dataLevel));
          dataLevel--;
          //if we are still traversing
          if (dataLevel > 0)
          {
            mapPerLevel.get(dataLevel).put(dataLevelIdentifier.get(dataLevel + 1), genericJSONHolder);
            dataLevelIdentifier.remove(dataLevel + 1);
          }
          //otherwise stop
          else
          {
            root.add(genericJSONHolder);
          }
        }
        //captures all other chars
        else if (identifierInitialised || valueInitialised)
        {
          if (builder != null)
          {
            builder.append(json.charAt(x));
          }
          else
          {
            throw new IllegalStateException(EXCEPTION_MESSAGE_SOMETHING_WENT_WRONG);
          }
        }
      }

      if (root.size() > 0)
      {
        return root;
      }
      else
      {
        throw new IllegalStateException("Something went wrong, a closing bracket seems missing");
      }
    }
    else
    {
      return new ArrayList<>();
    }
  }
}
