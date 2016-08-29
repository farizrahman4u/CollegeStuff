#include <GL/glut.h> 
#include <stdio.h>
#include<math.h>


int xa;
int xb;
int ya;
int yb;


void setPixel(GLint xc, GLint yc)
{
	glBegin(GL_POINTS);
		glVertex2f((float)xc / 500, (float)yc / 500);
	glEnd();
	glFlush();
}


void display()
{
	glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(0.0f, 0.0f, 0.0f);
	float dx = xb - xa;
	float dy = yb - ya;
	float _2dy = 2 * dy;
	float _2dym2dx = 2 * (dy - dx); 	
	int k = 0;
	float x_k = xa;
	float y_k = xb;
	float p_k = _2dy - dx;
	setPixel(xa, ya);                    
	for(k=0; k<dx; k++)
	{
		if(p_k < 0)
		{
			x_k += 1;
			p_k += _2dy;
			setPixel(x_k, y_k);
		}
		else
		{
			x_k += 1;
			y_k += 1;
			p_k += _2dym2dx;
			setPixel(x_k, y_k);
		}
	}




	glFlush();
	
}



int main(int argc, char** argv)
{
	printf("Enter coordinates : ");
	scanf("%d%d%d%d", &xa, &ya, &xb, &yb);
	glutInit(&argc, argv);
	glutCreateWindow("Bresenham line");
	glutInitWindowSize(500.0, 500.0);
	glutDisplayFunc(display); 
	glutMainLoop();
	return 0;
}


