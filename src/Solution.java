
import java.io.File;
import java.util.*;

public class Solution {
    static Map<Integer, Set<String>> rootClauses = new HashMap<>();
    static Map<Integer, Set<String>> sosClauses = new HashMap<>();
    static int lastLineNo = 0;
    static int pozicijaCilja = 0;
    static int odKudResolviramRoot = 0;
    static Set<String> newLiteral = new HashSet<>();
    static String finalNode = null;
    static Set<String> finish = null;

    public static void main(String[] args) {
        Set<String> clauseToResolve = new HashSet<>();
        String currentSoSClauseNegirano = null;
        Set<String> literalToAddTogether = new HashSet<>();
        boolean flag = false;
        int i = 0;

        if (args[0].equals("resolution")) {
            readFile (args[1]);

            while (!flag && i < rootClauses.size()+1) {

                for (i = odKudResolviramRoot; i<rootClauses.size()+1 && !flag; i++) {
                    clauseToResolve = rootClauses.get(i);
                    if (clauseToResolve.size() != 1) {
                        for (String sosLiteral : clauseToResolve) {
                            if (sosLiteral.contains("~")) {
                                currentSoSClauseNegirano = sosLiteral.replace("~", "");
                            }else
                                currentSoSClauseNegirano = "~" + sosLiteral;
                            plResolveMany(currentSoSClauseNegirano, odKudResolviramRoot, clauseToResolve);

                        }
                    }else {
                        for (String literal : clauseToResolve){
                            if (literal.contains("~")) {
                                currentSoSClauseNegirano = literal.replace("~", "");
                            }else
                                currentSoSClauseNegirano = "~" + literal;
                        }
                        //ovo ce mi dodat novo nastale resolvente u sosClauses
                        flag = plResolveOne (currentSoSClauseNegirano, odKudResolviramRoot);
                    }
                    //ovdje cu se provrtit po rootu
                    odKudResolviramRoot++;
                }
                //odKudResolviramRoot++;
            }
            if (i > rootClauses.size() ) {
                System.out.println("[CONCLUSION]: " + finalNode + " is unknown");
            }
        }

    }

    private static void plResolveMany(String currentSoSClauseNegirano, int odKudResolviramRoot, Set<String> clauseToResolve) {
        Set<String> currentRootLiterals = new HashSet<>();
        boolean stavi = true;
        for (int i = 1; i < odKudResolviramRoot; i++) {
            currentRootLiterals = rootClauses.get(i);
            //ako mi se slazu onda mi dodaj u sosclauses
            if (currentRootLiterals.contains(currentSoSClauseNegirano)) {
                for (String literal : currentRootLiterals) {
                    if (!literal.equals(currentSoSClauseNegirano)) {
                        newLiteral.add(literal);
                    }
                }
                for (String add : clauseToResolve) {
                    if (currentSoSClauseNegirano.contains("~")) {
                        if (!add.contains(currentSoSClauseNegirano.replace("~", ""))){
                            newLiteral.add(add);
                        }
                    }else {
                        if (!add.contains("~" + currentSoSClauseNegirano)) {
                            newLiteral.add(add);
                        }
                    }
                }
                if (rootClauses.containsValue(newLiteral))
                    stavi = false;
                for (String newLiteralElement : newLiteral) {
                    /*if (newLiteralElement.contains("~") && newLiteral.contains(newLiteralElement.replace("~", ""))
                    && !newLiteralElement.contains("~") && newLiteral.contains("~" + newLiteralElement)){
                        stavi = false;
                    }*/
                    if (newLiteralElement.contains("~")) {
                        if (newLiteral.contains(newLiteralElement.replace("~", ""))){
                            stavi = false;
                            break;
                        }
                    }else {
                        if (newLiteral.contains("~" + newLiteralElement)) {
                            stavi = false;
                            break;
                        }
                    }
                }
                if (stavi) {
                    System.out.println(rootClauses.size() + 1 + " Stavio > " + newLiteral + "> (" + odKudResolviramRoot + ", " + i + ")");
                    rootClauses.put(rootClauses.size() + 1, newLiteral);
                    newLiteral = new HashSet<>();
                }
            }
            stavi = true;
            newLiteral = new HashSet<>();
        }
        newLiteral = new HashSet<>();
    }

    private static boolean plResolveOne (String currentSoSClauseNegirano, int odKudResolviramRoot) {
        Set<String> currentRootLiterals = new HashSet<>();
        boolean stavi = true;
        for (int i = 1; i < odKudResolviramRoot; i++) {
            currentRootLiterals = rootClauses.get(i);
            //ako mi se slazu onda mi dodaj u sosclauses
            if (currentRootLiterals.contains(currentSoSClauseNegirano)) {
                for (String literal : currentRootLiterals) {
                    if (!literal.equals(currentSoSClauseNegirano)) {
                        newLiteral.add(literal);
                    }
                }

                if (newLiteral.isEmpty()) {
                    String arr[] = new String[finish.size()];
                    if (currentSoSClauseNegirano.contains("~")) {
                        currentSoSClauseNegirano = currentSoSClauseNegirano.replace("~", "");
                    }else
                        currentSoSClauseNegirano = "~" + currentSoSClauseNegirano;
                    System.out.println(currentSoSClauseNegirano + " sa " + currentRootLiterals + "   NIL  " + " > " + odKudResolviramRoot + ", " + i);
                    //if (finalNode.contains("~")) finalNode = finalNode.replace("~", "");
                    System.out.println("=====================");
                    System.out.println("[CONCLUSION]: " + finalNode + " is true");
                    System.out.println("=====================");
                    return true;
                } else {
                    if (rootClauses.containsValue(newLiteral))
                        stavi = false;
                    for (String newLiteralElement : newLiteral) {

                        if (newLiteralElement.contains("~")) {
                            if (newLiteral.contains(newLiteralElement.replace("~", ""))){
                                stavi = false;
                                break;
                            }
                        }else {
                            if (newLiteral.contains("~" + newLiteralElement)) {
                                stavi = false;
                                break;
                            }
                        }
                    }
                    if (stavi) {
                        System.out.println(rootClauses.size() + 1 + " Stavio > " + newLiteral + "> (" + odKudResolviramRoot + ", " + i + ")");
                        rootClauses.put(rootClauses.size() + 1, newLiteral);
                        newLiteral = new HashSet<>();
                    }
                    //rootClauses.put(rootClauses.size() + 1, newLiteral);
                    //newLiteral = new HashSet<>();
                }

            }
            stavi = true;
            newLiteral = new HashSet<>();
        }
        newLiteral = new HashSet<>();
        return false;
    }

    private static Set<Integer> resolveRoot(String currentSoSClauseNegirano, int odKudResolviramRoot) {
        Set<Integer> noOfPositionsMatched = new HashSet<>();
        for (int i = 1; i<odKudResolviramRoot; i++) {
            if (currentSoSClauseNegirano.contains("~")) {
                if (rootClauses.get(i).contains(currentSoSClauseNegirano))
                    noOfPositionsMatched.add(i);
            }else
            if (rootClauses.get(i).contains(currentSoSClauseNegirano) && !rootClauses.get(i).contains("~" + currentSoSClauseNegirano))
                noOfPositionsMatched.add(i);
        }
        return noOfPositionsMatched;
    }

    private static void readFile(String arg) {
        int lineNo = 1;
        //Set<String> finish = null;
        Set<String> lineLiterals = new HashSet<>();
        try {
            Scanner input = new Scanner(System.in);

            File file = new File(arg);

            input = new Scanner(file);
            while (input.hasNextLine()) {
                finalNode = input.nextLine().toLowerCase(Locale.ROOT);
                for (String line : finalNode.split(" v "))
                    lineLiterals.add(line);
                rootClauses.put (lineNo, lineLiterals);
                lineNo++;
                lineLiterals = new HashSet<>();
            }
            // zadnja (ciljna) linija
            finish = rootClauses.get(lineNo-1);
            //finalNode = finish.iterator().next();

            //System.out.println(finish);
            lastLineNo = lineNo-1;
            pozicijaCilja = lastLineNo;
            odKudResolviramRoot = pozicijaCilja;
            int i = 0;
            if (finish.size() > 1) {
                rootClauses.remove(pozicijaCilja);
                for (String line : finish) {
                    if (line.contains("~")) {
                        line = line.replace("~", "");
                        rootClauses.put(lastLineNo + i, new HashSet<String>(Collections.singleton(line)));
                    }else{
                        line = "~" + line;
                        rootClauses.put(lastLineNo + i, new HashSet<String>(Collections.singleton(line)));
                    }
                    i++;
                }

            }else {
                for (String line : finish) {
                    if (line.contains("~")) {
                        line = line.replace("~", "");
                        rootClauses.put(lastLineNo, new HashSet<String>(Collections.singleton(line)));
                    }else{
                        line = "~" + line;
                        rootClauses.put(lastLineNo, new HashSet<String>(Collections.singleton(line)));
                    }
                }
            }
            //rootClauses.remove(lineNo-1);*/
            input.close();
            System.out.println(rootClauses);
            System.out.println();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
