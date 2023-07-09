package clean_code._01_principle_.객체지향_생활체조_원칙._1;

public class Board {
    int[][] data;

    //step1) 2단 nested -> 지저분!
    String board1() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                buf.append(data[i][j]);
            buf.append("\n");
        }
        return buf.toString();
    }


    //case2) 로직 분리해서 2단 nested 없애기.
    String board2() {
        StringBuffer buf = new StringBuffer();
        collectRows(buf);
        return buf.toString();
    }

    void collectRows(StringBuffer buf) {
        for (int i = 0; i < 10; i++)
            collectRow(buf, i);
    }

    void collectRow(StringBuffer buf, int row) {
        for (int i = 0; i < 10; i++)
            buf.append(data[row][i]);
        buf.append("\n");
    }
}
