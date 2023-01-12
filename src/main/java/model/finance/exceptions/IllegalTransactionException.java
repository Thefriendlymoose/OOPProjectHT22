package model.finance.exceptions;


public class IllegalTransactionException extends Exception{

    public IllegalTransactionException(String errorMessage){
        super(errorMessage);
    }
}
