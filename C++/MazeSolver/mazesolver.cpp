#include <iostream>
#include <vector>

using namespace std;

const int N = 5;  // Maze size (NxN)
int maze[N][N] = {
    { 1, 0, 1, 1, 1 },
    { 1, 0, 1, 0, 1 },
    { 1, 1, 1, 0, 1 },
    { 0, 0, 0, 0, 1 },
    { 1, 1, 1, 1, 1 }
};

bool visited[N][N];  // Visited array to keep track of cells visited during DFS
vector<pair<int, int>> path;  // Store the path from start to end

bool isSafe(int x, int y) {
    // Check if x and y are within bounds, and the cell is not blocked and not visited
    return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1 && !visited[x][y]);
}

bool solveMaze(int x, int y) {
    if (x == N - 1 && y == N - 1) {  // Reached the end of the maze
        path.push_back({x, y});
        return true;
    }

    if (isSafe(x, y)) {
        // Mark the current cell as visited
        visited[x][y] = true;
        path.push_back({x, y});  // Add the cell to the path

        // Move right
        if (solveMaze(x, y + 1)) return true;

        // Move down
        if (solveMaze(x + 1, y)) return true;

        // Move left
        if (solveMaze(x, y - 1)) return true;

        // Move up
        if (solveMaze(x - 1, y)) return true;

        // Backtrack: unmark the current cell and remove from the path
        path.pop_back();
        visited[x][y] = false;
    }
    return false;
}

int main() {
    // Initialize visited array
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            visited[i][j] = false;

    if (solveMaze(0, 0)) {
        cout << "Maze Solved! The path is: " << endl;
        for (auto p : path) {
            cout << "(" << p.first << "," << p.second << ") ";
        }
        cout << endl;
    } else {
        cout << "No solution exists!" << endl;
    }

    return 0;
}
