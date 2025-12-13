package com.antibas.util.problems;

import java.util.ArrayList;
import java.util.List;

public interface Problem<S, A> {
	S getStartState();
	A getFinalAction();
	boolean isGoalState(S state);
	List<? extends Successor<S, A>> getSuccessors(S state);
	double getCostOfActions(List<A> actions);
	default List<A> backtrackSuccessors(List<? extends Successor<S,A>> successors){
		int i = successors.size()-1, j = successors.size()-2;
		
		while(i >= 0){
			if(!areCloseStates(successors.get(i).getState(), successors.get(j).getState())) {
				successors.remove(i);
			} 
			else {
				i--; j--;
			}
		}
		
		List<A> actions = new ArrayList<>();
		for(Successor<S, A> successor: successors) {
			actions.add(successor.getAction());
		}
		actions.add(getFinalAction());
		return actions;
	}
	default boolean areCloseStates(S state1, S state2) {
		for(Successor<S, A> successor: this.getSuccessors(state1)) {
			if(successor.getState().equals(state2)) {
				return true;
			}
		}
		
		for(Successor<S, A> successor: this.getSuccessors(state2)) {
			if(successor.getState().equals(state1)) {
				return true;
			}
		}
		
		return false;
	}
}
