#define _CRT_SECURE_NO_DEPRECATE

#include<stdio.h>
#include<stdlib.h>
#include<memory.h>


int** createArray(int x, int y);
void initArray(int** c, int x, int y);
void lengthLCS(int** c, char* str1, int length1, char* str2, int length2);
void printLCS(int** c, char* str1, int length1, int length2);

int main(void) {
	
	char* seq1 = "abcad";
	char* seq2 = "acbda";

	int length1, length2;

	int** c;

	length1 = _mbstrlen(seq1);
	length2 = _mbstrlen(seq2);

	printf("!] Start LCS \n");
	printf("input seq1 = %s \t seq2 = %s\n", seq1, seq2);

	c = createArray(length1+1, length2+1);
	initArray(c, length1+1, length2+1);
	
	lengthLCS(c, seq1, length1, seq2, length2);

	printf("======= Result =======\n");
	printLCS(c, seq1, length1, length2);
	printf("\n======================\n");

}
void printLCS(int** c, char* str1, int i, int j) {
	if (i == 0 || j == 0) {
		return;
	}

	if (c[i][j] - 1 == c[i - 1][j - 1] && c[i][j] - 1 == c[i][j - 1] && c[i][j] - 1 == c[i - 1][j]) {
		printLCS(c, str1, i - 1, j - 1);
		printf("%c",str1[i - 1]);
	}
	else if (c[i][j] == c[i - 1][j]) {
		printLCS(c, str1, i - 1, j);
	}
	else {
		printLCS(c, str1, i, j - 1);
	}
}

void lengthLCS(int** c, char* str1, int length1, char* str2, int length2) {
	for (int i = 1; i <= length1; i++) {
		for (int j = 1; j <= length2; j++) {
			if (str1[i - 1] == str2[j - 1]) {
				c[i][j] = c[i - 1][j] + 1;
			}
			else if (c[i - 1][j] >= c[i][j - 1]) {
				c[i][j] = c[i - 1][j];
			}
			else {
				c[i][j] = c[i][j - 1];
			}
		}
	}
}

int** createArray(int x, int y) {
	int **temp;

	temp = (int**)malloc(sizeof(int*)*x);

	for (int i = 0; i < x; i++) {
		temp[i] = (int*)malloc(sizeof(int)*y);
	}

	return temp;
}

void initArray(int** c, int x, int y) {
	for (int i = 0; i < x; i++) {
		for (int j = 0; j < y; j++) {
			if (i == 0 || j == 0) {
				c[i][j] = 0;
			}
			else {
				c[i][j] = -1;
			}
		}
	}
}