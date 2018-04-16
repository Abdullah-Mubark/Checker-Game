/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker.game;

import java.util.ArrayList;

/**
 *
 * @author Tauta
 */
public class AlphaBeta {
    public int MAX_DEPTH;
    BoardEvaluation boardEvaluator = new BoardEvaluation();
    Move bestMove;
    AlphaBeta(int diff){
    if(diff==1){
        MAX_DEPTH = 6;
    }else if (diff == 0){
        MAX_DEPTH = 2;
    }
    }
    
    public int alphaBeta(Board board, PlayerType player, int depth, int alpha, int beta){
        
        if(!canExploreFurther(board, player, depth)){
            int value = boardEvaluator.evaluateBoard(board, player);
            return value;
        }
        
        ArrayList<Move> possibleMoveSeq;
        ArrayList<Board> possibleBoardConf;
        
        if(player == PlayerType.WHITE){
            White w = new White();
            w.calculateAllpossibleMoves(board);
            possibleMoveSeq = w.allowedMoves;
        } else {
            Black b = new Black();
            b.calculateAllpossibleMoves(board);
            possibleMoveSeq = b.allowedMoves;
        }
        
        possibleBoardConf = getBoards(board, possibleMoveSeq);
        
        Move bestMoveSeq = null;
        
        if(player == PlayerType.WHITE){            
            
            for(int i=0; i<possibleBoardConf.size(); i++){
                
                Board b = possibleBoardConf.get(i);
                Move moveSeq = possibleMoveSeq.get(i);
                
                int value = alphaBeta(b, PlayerType.BLACK, depth+1, alpha, beta);

                if(value > alpha){
                    alpha = value;
                    bestMoveSeq = moveSeq;
                }
                if(alpha>beta){
                    break;
                }
            }
            
            //If the depth is 0, copy the bestMoveSeq in the result move seq.
            if(depth == 0 && bestMoveSeq!=null){
                bestMove = bestMoveSeq;
            }

            return alpha;
            
        }else{
            assert(player == PlayerType.BLACK);
            
            for(int i=0; i<possibleBoardConf.size(); i++){
               
                Board b = possibleBoardConf.get(i);
                Move moveSeq = possibleMoveSeq.get(i);
                
                int value = alphaBeta(b, PlayerType.BLACK, depth+1, alpha, beta);
                if(value < beta){
                    bestMoveSeq = moveSeq;
                    beta = value;
                }
                if(alpha>beta){
                    break;
                }
            }
            //If the depth is 0, copy the bestMoveSeq in the result move seq.
            if(depth == 0 && bestMoveSeq!=null){
                bestMove = bestMoveSeq;
            }

            return beta;
        }        
    }
    
    public ArrayList<Board> getBoards(Board board,ArrayList<Move> moves){
        ArrayList<Board> possibleBoards = new ArrayList<>();
            for(Move m: moves){
                Board newBoard = new Board(board.getBoard());
                newBoard.makeMove(m);
                possibleBoards.add(newBoard);
            }
            return possibleBoards;
    }
    
    
    public Board getBoard(Board board, Move move){
        Board newBoard = new Board(board.getBoard());
        newBoard.makeMove(move);
        
        return newBoard;
    }
    
    public boolean canExploreFurther(Board board, PlayerType player, int depth){
        boolean res = true;
        if(board.CheckGameComplete()  || board.CheckGameDraw(player)){
            res = false;
        }
        if(depth == MAX_DEPTH){
            res = false;
        }
        return res;
    }
    
}
