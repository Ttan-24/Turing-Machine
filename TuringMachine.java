/*

We need an array which is the tape

Then we read the tape

We need to be able to read a symbol and do something based on the instruction

Need a concept of "state", instruction, instruction table, etc.

State	/ Symbol Read	/ Write Instruction	/ Move Instruction / Next State

*/

////// State Table /////////////

import java.util.ArrayList;
import java.util.Arrays;

enum ESymbols {
  ZERO,
  ONE,
  BLANK,
}

enum EDirections {
  NONE,
  LEFT,
  RIGHT,
}

class InstructionSet {
  public InstructionSet(Integer _symbolRead, Integer _writeInstruction, Integer _moveInstruction, Integer _nextState) {
    symbolRead = _symbolRead;
    writeInstruction = _writeInstruction;
    moveInstruction = _moveInstruction;
    nextState = _nextState;
  }

  Integer symbolRead = null;
  Integer writeInstruction = null;
  Integer moveInstruction = null;
  Integer nextState = null;
}

class State {
  public ArrayList<InstructionSet> instructionSets = new ArrayList<>();

  public State(InstructionSet _instructionSet1, InstructionSet _instructionSet2,
      InstructionSet _instructionSet3) {
    instructionSets.add(_instructionSet1);
    instructionSets.add(_instructionSet2);
    instructionSets.add(_instructionSet3);
  }

}

class StateTable {
  public ArrayList<State> states = new ArrayList<>();
}

///////////// Turing Machine ///////////////////

class Tape {
  public Tape(ArrayList<Integer> _symbols) {
    symbols = _symbols;
  }

  public ArrayList<Integer> symbols;
}

class TuringMachine {
  // Constructor
  TuringMachine(Tape _tape, StateTable _stateTable) {
    tape = _tape;
    stateTable = _stateTable;
  }

  public void PrintTape(int tapeIndex) {
    System.out.print("Tape: ");
    for (int i = 0; i < tape.symbols.size(); i++) {
      if (i == tapeIndex) {
        System.out.print("*");
        System.out.print(tape.symbols.get(i));
        System.out.print("*");
      } else {
        System.out.print(" ");
        System.out.print(tape.symbols.get(i));
        System.out.print(" ");
      }
    }
    System.out.print("    ");
  }

  public void run() {
    int tapeIndex = 1;

    State currentState = stateTable.states.get(0);
    boolean running = true;
    while (running) {
      // Get symbol
      int tapeSymbol = tape.symbols.get(tapeIndex);

      PrintTape(tapeIndex);

      // Check instructions set for symbol
      for (InstructionSet instructionSet : currentState.instructionSets) {
        if (instructionSet.symbolRead == tapeSymbol) {

          // Execute write instruction
          System.out.print("Output: ");
          System.out.println(instructionSet.writeInstruction);
          tape.symbols.set(tapeIndex, instructionSet.writeInstruction);
          tapeIndex += instructionSet.moveInstruction;

          // State transition
          Integer nextStateIndex = instructionSet.nextState;
          if (nextStateIndex == -1) {
            running = false;
          } else {
            currentState = stateTable.states.get(nextStateIndex);
          }

          // Exit
          break;
        }
      }
    }
  }

  Tape tape;
  StateTable stateTable;
}

///////////// Main Code ///////////////////////

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
		////////////////////////////////Add one////////////////////////////
    // // State 1
    // State state1 = new State(new InstructionSet(-1, -1, -1, 1),
    //     new InstructionSet(0, 0, 1, 0),
    //     new InstructionSet(1, 1, 1, 0));

    // // State 2
    // State state2 = new State(new InstructionSet(-1, 1, 1, 2),
    //     new InstructionSet(0, 1, -1, 2),
    //     new InstructionSet(1, 0, -1, 1));

    // // State 3
    // State state3 = new State(new InstructionSet(-1, -1, -1, -1),
    //     new InstructionSet(0, 0, 1, 2),
    //     new InstructionSet(1, 1, 1, 2));

		/////////////////////////////////Sub one////////////////////
		// // State 1
		// State state1 = new State(new InstructionSet(-1, -1, -1, 1),
		// 		new InstructionSet(0, 0, 1, 0),
		// 		new InstructionSet(1, 1, 1, 0));

		// // State 2
		// State state2 = new State(new InstructionSet(-1, 1, 1, 2),
		// 		new InstructionSet(0, 1, -1, 1),
		// 		new InstructionSet(1, 0, -1, 2));

		// // State 3
		// State state3 = new State(new InstructionSet(-1, -1, -1, -1),
		// 		new InstructionSet(0, 0, 1, 2),
		// 		new InstructionSet(1, 1, 1, 2));


		////////////////////////////////Addition ////////////////////////////

    // Subtraction states
		State readLeftToRightState = new State(new InstructionSet(-1, -1, -1, 1),
		    new InstructionSet(0, 0, 1, 0),
		    new InstructionSet(1, 1, 1, 0));
		State subtractionReplaceState = new State(new InstructionSet(-1, 1, 1, 2),
		    new InstructionSet(0, 1, -1, 1),
		    new InstructionSet(1, 0, -1, 2));
		State jumpLeftToRightState = new State(new InstructionSet(-1, -1, 1, 3),
		    new InstructionSet(0, 0, 1, 2),
		    new InstructionSet(1, 1, 1, 2));

    // Addition states
		State readLeftToRightState2 = new State(new InstructionSet(-1, -1, -1, 4),
				new InstructionSet(0, 0, 1, 3),
				new InstructionSet(1, 1, 1, 3));
		State additionReplaceState = new State(new InstructionSet(-1, 1, 1, 5),
				new InstructionSet(0, 1, -1, 5),
				new InstructionSet(1, 0, -1, 4));
		State exitRightState = new State(new InstructionSet(-1, -1, -1, -1),
				new InstructionSet(0, 0, 1, 5),
				new InstructionSet(1, 1, 1, 5));

    /* 
    New behaviour we need:
    - Jump over
    */


    //////////////////////////////// Palindrome ////////////////////////////
		
    // State state0 = new State(new InstructionSet(-1, -1, -1, 1),
    // new InstructionSet(0, 0, 1, 0),
    // new InstructionSet(1, 1, 1, 0));

    // State state1 = new State(new InstructionSet(-1, 1, 0, -1),
    // new InstructionSet(0, -1, -1, 2),
    // new InstructionSet(1, -1, -1, 4));

    // State state2 = new State(new InstructionSet(-1, -1, 1, 3),
    // new InstructionSet(0, 0, -1, 2),
    // new InstructionSet(1, 1, -1, 2));

    // State state3 = new State(new InstructionSet(-1, -1, 0, 0),
    // new InstructionSet(0, -1, 1, 0),
    // new InstructionSet(1, 1, 0, 6));

    // State state4 = new State(new InstructionSet(-1, -1, 1, 5),
    // new InstructionSet(0, 0, -1, 4),
    // new InstructionSet(1, 1, -1, 4));

    // State state5 = new State(new InstructionSet(-1, -1, 0, 0),
    // new InstructionSet(0, 0, 0, 6),
    // new InstructionSet(1, 0, 1, 0));

    // State state6 = new State(new InstructionSet(-1, 0, 0, -1),
    // new InstructionSet(0, 0, 1, 6),
    // new InstructionSet(1, 0, 1, 6));

    // State table
    StateTable stateTable = new StateTable();
    // stateTable.states.add(state0);
    stateTable.states.add(readLeftToRightState);
    stateTable.states.add(subtractionReplaceState);
    stateTable.states.add(jumpLeftToRightState);
    stateTable.states.add(readLeftToRightState2);
    stateTable.states.add(additionReplaceState);
    stateTable.states.add(exitRightState);

    // Turing machine
    Tape tape = new Tape(new ArrayList<Integer>(Arrays.asList(-1, 0, 1, 1, 1, -1, 0, 1, 1, 0, -1)));
    TuringMachine turingMachine = new TuringMachine(tape, stateTable);
    turingMachine.run();
  }
}

/*
7 + 6 = 13
0111   0110
0110	 0111
0101   1000
0100   1001
0011   1010
0010   1011
0001   1100
0000   1101
*/