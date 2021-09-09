package by.itransition.training.task03;

public class Referee {

    public int getUserResult(int length, int userMove, int machineMove) {
        int lowerHalf = length / 2;
        if (machineMove == userMove) {
            return 0;
        } else {
            for (int i = 1; i <= lowerHalf; i++) {
                int temp = userMove;
                temp += i;
                if (temp > length) {
                    temp -= length;
                }
                if (temp == machineMove) {
                    return 1;
                }
            }
            return -1;
        }
    }
}