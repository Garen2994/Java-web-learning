#include <iostream>


/*leetcode #169 Valid Suduko*/

using namespace std;

class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        for (auto i = 0; i < 9; ++i) {
            auto rowFlag = vector<bool>(10, false);
            auto colFlag = vector<bool>(10, false);
            auto blockFlag = vector<bool>(10, false);
            for (auto j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    auto num = board[i][j] - '0';
                    if (rowFlag[num]) return false;
                    rowFlag[num] = true;
                }
                if (board[j][i] != '.') {
                    auto num = board[j][i] - '0';
                    if (colFlag[num]) return false;
                    colFlag[num] = true;
                }
                auto row = (i/3)*3 + j/3;
                auto col = (i%3)*3 + j%3;
                if (board[row][col] != '.') {
                    auto num = board[row][col] - '0';
                    if (blockFlag[num]) return false;
                    blockFlag[num] = true;
                }
            }
        }
        return true;
    }
} solution;
void main(){
    board = [
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
    solution.isValidSudoku(&board);


}
