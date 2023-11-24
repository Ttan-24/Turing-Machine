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

    public State(InstructionSet _instructionSet1, InstructionSet _instructionSet2, InstructionSet _instructionSet3) {
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
    //Constructor
    TuringMachine(Tape _tape, StateTable _stateTable) {
      tape = _tape;
      stateTable = _stateTable;
    }
  
    public void run()
    {
      int tapeIndex = 0;
      
      State currentState = stateTable.states.get(0);
      boolean running = true;
      while (running)
      {
        // Get symbol
        int tapeSymbol = tape.symbols.get(tapeIndex);
  
        // Check instructions set for symbol
        for(InstructionSet instructionSet : currentState.instructionSets)
        {
          if(instructionSet!=null) {
            
          }
          if (instructionSet.symbolRead == tapeSymbol)
          {
            // Execute instruction
            System.out.println(instructionSet.writeInstruction);
            tapeIndex += instructionSet.moveInstruction;
  
            // State transition
            State nextState = stateTable.states.get(instructionSet.nextState);
            if (nextState == null)
            {
              running = false;
            }
            else
            {
              currentState = nextState;
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
  
      // State 1
      InstructionSet instructionSet1 = new InstructionSet(null, null, 1, 1);
      InstructionSet instructionSet2 = new InstructionSet(0, 0, -1, 0);
      InstructionSet instructionSet3 = new InstructionSet(1, 1, -1, 0);
      State state = new State(instructionSet1, instructionSet2, instructionSet3);
  
      // State table
      StateTable stateTable = new StateTable();
      stateTable.states.add(state);
      
      // Turing machine
      Tape tape = new Tape(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 0, 1, 1, 0, 1)));
      TuringMachine turingMachine = new TuringMachine(tape, stateTable);
      turingMachine.run();
    }
  }