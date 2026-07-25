package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
        // [((())), (()()), (())(), ()(()), ()()()]
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, String current, int openCount, int closeCount, int n) {
        if (openCount == n && closeCount == n) {
            result.add(current);
            return;
        }

        if (openCount < n) {
            backtrack(result, current + "(", openCount + 1, closeCount, n);
        }

        if (closeCount < openCount) {
            backtrack(result, current + ")", openCount, closeCount + 1, n);
        }
    }
}

/*

What each part is doing:

* if (openCount == n && closeCount == n) — exactly the stopping point you were reaching for: both counters maxed out means the string is complete and valid. We add it to result and return (stop going deeper on this path).

* if (openCount < n) — your rule #1: only add another ( if we haven't hit the limit yet.

* if (closeCount < openCount) — this is the sharpened version of your rule #2: we can add a ) only if the number of closes so far is less than the number of opens so far. That's precisely "there's an unmatched open bracket still waiting."

* This function is called with two recursive calls at each step. Here, you're exploring two different paths (try adding (, try adding )), and each path keeps branching until it either completes or becomes illegal to continue.

*** Recursion Call Tree ***
--------------------------

Generate Parentheses — Backtracking Tree (n = 2)
backtrack(current, openCount, closeCount, n)
Rule 1: add '(' only if openCount < n
Rule 2: add ')' only if closeCount < openCount
Base case: openCount == n AND closeCount == n → record answer

                          backtrack("", 0, 0)
                                   |
                    [open<n? YES] |
                                   |
                          backtrack("(", 1, 0)
                          /                    \
        [open<n? YES]   /                      \  [close<open? YES]
                        /                        \
            backtrack("((", 2, 0)          backtrack("()", 1, 1)
                       |                         |
      [open<n? NO — skip]                [open<n? YES]
      [close<open? YES]                          |
                       |                          |
            backtrack("(()", 2, 1)          backtrack("()(", 2, 1)
                       |                         |
      [open<n? NO — skip]              [open<n? NO — skip]
      [close<open? YES]                [close<open? YES]
                       |                         |
            backtrack("(())", 2, 2)        backtrack("()()", 2, 2)
                  ✅ BASE CASE                 ✅ BASE CASE
              record: "(())"                record: "()()"

Result: ["(())", "()()"]

 */