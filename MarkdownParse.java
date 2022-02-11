
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {

            int backticks = markdown.indexOf("```", currentIndex);
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
<<<<<<< HEAD

            // No links can be found inside code block
            if(backticks != -1 && backticks < nextOpenBracket) {
                int nextBackticks = markdown.indexOf("```", backticks+1);
                if (nextBackticks == -1) {
                    break;
                }
                currentIndex = nextBackticks+1;
                continue;
            }
=======
            
>>>>>>> parent of 5942e12 (fix test 2)
            
            if(nextOpenBracket == -1){
                break;
            }

            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);

            if(nextOpenBracket >= 1 && markdown.charAt(nextOpenBracket-1) == '!') {
                currentIndex = closeParen + 1;
                continue;
            }

            if(openParen != nextCloseBracket + 1) {
                currentIndex = nextOpenBracket + 1;
                continue;
            }

            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
} 