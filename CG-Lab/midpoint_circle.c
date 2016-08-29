#include <GL/glut.h> 
#include <stdio.h>
#include<math.h>


int xc;
int yc;
int rc;

void setPixel(GLint _xc, GLint _yc)
{
	glBegin(GL_POINTS);
		glVertex2f((float)(_xc) / 500, (float)(_yc) / 500);
	glEnd();
	glFlush();
}


void display()
{
	glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(0.0f, 0.0f, 0.0f);
	int x_k = 0;
	int y_k = rc;
	float p_k = (5.0 / 4) - rc;
	while(x_k < y_k)
	{
		if(p_k < 0)
		{
			x_k += 1;
			p_k += 2 * x_k + 1;
		}
		else
		{
			x_k += 1;
			y_k -= 1;
			p_k += 2 * x_k + 1 - 2 * y_k;
		}
		setPixel(x_k + xc, y_k + yc);
		setPixel(y_k + xc, x_k + yc);
		setPixel(x_k + xc, -y_k + yc);
		setPixel(y_k + xc, -x_k + yc);
		setPixel(-x_k + xc, y_k + yc);
		setPixel(-y_k + xc, x_k + yc);
		setPixel(-x_k + xc, -y_k + yc);
		setPixel(-y_k + xc, -x_k + yc);
	}
	
	glFlush();
	
}



int main(int argc, char** argv)
{
	printf("Enter center coordinates : ");
	scanf("%d%d", &xc, &yc);
	printf("Enter radius : ");
	scanf("%d", &rc);
	glutInit(&argc, argv);
	glutCreateWindow("Midpoint circle algorithm");
	glutInitWindowSize(500.0, 500.0);
	glutDisplayFunc(display); 
	glutMainLoop();
	return 0;
}


