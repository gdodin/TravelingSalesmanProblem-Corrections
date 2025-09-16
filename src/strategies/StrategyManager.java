package strategies;

import strategies.exact.*;
import strategies.heuristics.basic.DefaultPathStrategy;

public class StrategyManager {

	public static Soluce createSolucePath(SoluceType soluceType) {

		switch(soluceType) {
		case DEFAULT:return new DefaultPathStrategy().create();
		case RANDOM:return new RandomPathStrategy().create();
		case XRANDOM:return new XRandomPathStrategy().create();
		case NN:return new NNPathStrategy().create();
		case BEST_NN:return new BestNnStrategy().create();
		case DFS:return new DfsStrategy().create();
		case DFS_WITH_PRECEDENCE:return new DfsWithPrecedenceStrategy().create();
		case DFS_WITH_PRECEDENCE_WITH_MAX:return new DfsWithPrecedenceWithMaxStrategy().create();
		case BAB:return new BABPathStrategy().create();
        case DPS:return new DPStrategy().create();
		default: return null;
		}
	}

}
