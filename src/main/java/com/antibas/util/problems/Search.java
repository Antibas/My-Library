package com.antibas.util.problems;

import java.util.*;

public final class Search {
	public static <S, A> List<A> depthFirstSearch(Problem<S, A> problem){
		List<Successor<S, A>> actionSuccessors = new ArrayList<>();
		if(problem.isGoalState(problem.getStartState())) {
			List<A> actions = new ArrayList<>();
			actions.add(problem.getFinalAction());
			return actions;
		}
		
		Stack<Successor<S,A>> stack = new Stack<>();
		stack.addAll(problem.getSuccessors(problem.getStartState()));
		
		Set<S> exploredStates = new HashSet<>();
		exploredStates.add(problem.getStartState());
		
		while(!stack.isEmpty()) {
			Successor<S,A> currentSucc = stack.pop(); 
			
			exploredStates.add(currentSucc.getState());
			
			actionSuccessors.add(currentSucc);
			
			if(problem.isGoalState(currentSucc.getState())) {
				return problem.backtrackSuccessors(actionSuccessors);
			}
			
			List<Successor<S,A>> expSuccs = new ArrayList<>();
			for(Successor<S,A> successor: problem.getSuccessors(problem.getStartState())) {
				if(!exploredStates.contains(successor.getState()) && !stack.contains(successor)) {
					expSuccs.add(successor);
				}
			}
			stack.addAll(stack.size(), expSuccs);
		}
		return null;
	}
	public static <S, A> List<A> breadthFirstSearch(Problem<S, A> problem){
		List<Successor<S, A>> actionSuccessors = new ArrayList<>();
		if(problem.isGoalState(problem.getStartState())) {
			List<A> actions = new ArrayList<>();
			actions.add(problem.getFinalAction());
			return actions;
		}
		
		Queue<Successor<S,A>> queue = new LinkedList<>();
		queue.addAll(problem.getSuccessors(problem.getStartState()));
		
		return null;
	}
	public static <S, A> List<A> AStarSearch(Problem<S, A> problem, Heuristic<S, A> heuristic){
		return null;
	}
	
	public static <S, A> List<A> AStarSearch(Problem<S, A> problem){
		return Search.AStarSearch(problem, x -> 0d);
	}
	
	

}
