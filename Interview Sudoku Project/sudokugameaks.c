#include<stdio.h>
#include<conio.h>
#include<stdlib.h>
int rowcheck(int array[9][9]) {
	int c[10] = {0};
	for(int i = 0; i < 9; i++) {
		for(int j = 0; j < 9 ; j++) {
			c[array[i][j]]++;
		}
		for(int k = 1; k <= 9; k++)
			if(c[k] != 1) {
				printf("\nThe value %d came %d times in %d row\n",k,c[k],i+1);
			}
		for(int k = 1; k <= 9 ; k++)
			c[k] = 0;
	}
	return 1;
}
int colcheck(int array[9][9]) {
	int c[10] = {0};
	for(int i = 0; i < 9; i++) {
		for(int j = 0; j < 9; j++) {
			c[array[j][i]]++;
		}
		for(int k = 1; k <= 9; k++)
			if(c[k] != 1) {
				printf("\nThe value %d came %d times in %d coloumn\n",k,c[k],i+1);
				return 0;
			}
		for(int k = 1; k <= 9; k++)
			c[k] = 0;
	}
	return 1;
}
int cubecheck(int array[9][9]) {
	int c[10] = {0}, count = 0;
	for(int m = 0; m < 9; m += 3) {
		for(int b = 0; b < 9; b += 3) {
			for(int i = m; i < m + 3; i++) {
				for(int j = b; j < b + 3; j++) {
					c[array[i][j]]++;
				}
			}
			count++;
			for(int  k = 1 ; k <= 9; k++)
				if(c[k] != 1) {
					printf("\nThe value %d came %d times in %d box\n",k,c[k], count);
					return 0;
				}
			for(int k = 1; k <= 9; k++)
				c[k] = 0;
		}
	}
	return 1;
}
int main() {
input:
	int rows = 9;
    int cols = 9;
    int array[9][9];
    
    printf("Enter Elements Of Sudoku Game : \n");
    for(int i = 0; i < 9; i++){
    	for(int j = 0; j < 9; j++){
    		scanf("%d", &array[i][j]);
		}
	}
	printf("\nThe Sudoku Game Is : \n");
	// Loop to print the pattern with array elements
	for (int i = 0; i < rows; i++) {
		// Print horizontal lines
		for (int j = 0; j < cols; j++) {
			printf(" ----");
		}
		printf("\n");

		// Print vertical lines with array elements
		for (int k = 0; k < cols; k++) {
			printf("| %2d ", array[i][k]);
		}
		printf("|\n");
	}

	// Print the last horizontal line
	for (int l = 0; l < cols; l++) {
		printf(" ----");
	}
	printf("\n");

	if(rowcheck(array) == 1 && colcheck(array) == 1 && cubecheck(array) == 1)
		printf("\nGame Is Sucessful Game");
	else
		printf("\nGame Is Unsucessful Game");
	printf("\nPress Enter To Restart Game Press Esc To Exit The game : ");
int ch = getch();
if(ch == 32){
	system("cls");
	goto input;
}
else if(ch == 27){
	exit(0);
}
}