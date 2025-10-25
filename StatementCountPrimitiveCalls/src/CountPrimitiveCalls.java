import components.statement.Statement;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Brayden May
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                int length = s.lengthOfBlock();
                for (int i = 0; i < length; i++) {
                    Statement currentStatement = s.removeFromBlock(0);
                    count += countOfPrimitiveCalls(currentStatement);
                    s.addToBlock(s.lengthOfBlock(), currentStatement);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                Statement label = s.newInstance();

                Statement.Condition c = s.disassembleIf(label);
                count = countOfPrimitiveCalls(label);

                s.assembleIf(c, label);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                Statement labelIf = s.newInstance();
                Statement labelElse = s.newInstance();

                Statement.Condition c = s.disassembleIfElse(labelIf, labelElse);

                count = countOfPrimitiveCalls(labelIf) + countOfPrimitiveCalls(labelElse);

                s.assembleIfElse(c, labelIf, labelElse);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                Statement label = s.newInstance();

                Statement.Condition c = s.disassembleWhile(label);

                count = countOfPrimitiveCalls(label);

                s.assembleWhile(c, label);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                String instruction = s.disassembleCall();

                switch (instruction) {
                    case "turnleft":
                    case "turnright":
                    case "move":
                    case "skip":
                    case "infect": {
                        count++;
                        break;
                    }
                    default:
                }

                s.assembleCall(instruction);

                break;
            }
            default: {
                // this is never supposed to happen
                // every kind of statement is contained is the switch
                break;
            }
        }
        return count;
    }

}
