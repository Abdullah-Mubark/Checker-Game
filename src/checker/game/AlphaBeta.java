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
    static int MAX_DEPTH = 6;
    BoardEvaluation boardEvaluator = new BoardEvaluation();
    
    AlphaBeta(){}
    
    public int alphaBeta(Board board, PlayerType player, int depth, int alpha, int beta, Move move){
        
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
                
                int value = alphaBeta(b, PlayerType.BLACK, depth+1, alpha, beta, move);

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
                move = bestMoveSeq;
            }

            return alpha;
            
        }else{
            assert(player == PlayerType.BLACK);
            
            for(int i=0; i<possibleBoardConf.size(); i++){
               
                Board b = possibleBoardConf.get(i);
                Move moveSeq = possibleMoveSeq.get(i);
                
                int value = alphaBeta(b, PlayerType.BLACK, depth+1, alpha, beta, move);
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
                move = bestMoveSeq;
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
    
    public static boolean canExploreFurther(Board board, PlayerType player, int depth){
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
