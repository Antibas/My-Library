package com.antibas.util.problems;

import com.antibas.util.pair.Pair;

import java.util.*;

public class PositionProblem implements Problem<Pair<Integer, Integer>, PositionProblem.Direction> {
	private Pair<Integer, Integer> startState, goalState;
	private List<Pair<Integer, Integer>> walls;
	private Heuristic<Pair<Integer, Integer>, Direction> costFunction;
	private Set<Pair<Integer, Integer>> visited;
	private int expandedNum;
	
	public PositionProblem(Pair<Integer, Integer> startState, Pair<Integer, Integer> goalState, Heuristic<Pair<Integer, Integer>, Direction> costFunction, Collection<? extends Pair<Integer, Integer>> walls) {
		this.startState = startState;
		this.goalState = goalState;
		this.walls = new ArrayList<>(walls);
		this.costFunction = costFunction;
		this.visited = new HashSet<>();
		this.expandedNum = 0;
	}
	
	public PositionProblem(Pair<Integer, Integer> startState, Pair<Integer, Integer> goalState, Collection<? extends Pair<Integer, Integer>> walls) {
		this(startState, goalState, x -> 1.0, walls);
	}

	@Override
	public Pair<Integer, Integer> getStartState() {
		return startState;
	}

	@Override
	public Direction getFinalAction() {
		return Direction.STOP;
	}
	
	@Override
	public boolean isGoalState(Pair<Integer, Integer> state) {
		return state.equals(goalState);
	}

	@Override
	public List<Successor<Pair<Integer, Integer>, Direction>> getSuccessors(Pair<Integer, Integer> state) {
		ArrayList<Successor<Pair<Integer, Integer>, Direction>> successors = new ArrayList<>();
		Direction[] directions = Direction.values();
		
		for(int i = 0; i < directions.length-1; i++) {
			Pair<Integer, Integer> next = new Pair<>(state.getFirst() + directions[i].vector.getFirst(), state.getSecond() + directions[i].vector.getSecond());
			//PositionSuccessor next;
			if(!walls.contains(next)){
				successors.add(new Successor<Pair<Integer, Integer>, Direction>(next, directions[i], this.costFunction));
			}
		}
		
		this.expandedNum++;
		if(!visited.contains(state)) {
			visited.add(state);
		}
		return successors;
	}

	@Override
	public double getCostOfActions(List<Direction> actions) {
		if(actions.isEmpty()) {
			return Double.POSITIVE_INFINITY;
		}
		
		int x = this.startState.getFirst(), y = this.startState.getSecond();
		double cost = 0;
		
		for(Direction action: actions) {
			x += action.vector.getFirst();
			y += action.vector.getSecond();
			Successor<Pair<Integer, Integer>, Direction> next = new Successor<Pair<Integer, Integer>, Direction>(new Pair<>(x,y), null);
			if(walls.contains(next.getState())) {
				return Double.POSITIVE_INFINITY;
			}
			cost += this.costFunction.apply(next);
		}
		return cost;
	}

	@Override
	public boolean areCloseStates(Pair<Integer, Integer> state1, Pair<Integer, Integer> state2) {
		int x1 = state1.getFirst(), y1 = state1.getSecond(), x2 = state2.getFirst(), y2 = state2.getSecond();
		int dx = Math.abs(x1-x2), dy = Math.abs(y1-y2);
		return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
	}
	
	public int getExpandedNum() {
		return expandedNum;
	}
	
	public static enum Direction {
		NORTH(0, 1), SOUTH(0, -1), EAST(1, 0), WEST(-1, 0), STOP(0, 0);
		public final Pair<Integer, Integer> vector;

		private Direction(int x, int y) {
			this.vector = new Pair<>(x, y);
		}
	}
}
