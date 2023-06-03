package Model;

public enum TileType {

    P1, P2, EMPTY;

    @Override
    public String toString(){
        if(this == P1){
            return "X";
        }
        else if( this == P2){
            return "Y";
        }
        return "-";
    }


}
