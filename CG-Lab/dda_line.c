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
	// Slope
	float dx = xb - xa;
	float dy = yb - ya;
	float nb_steps;
	if(dx > dy)
	{
		nb_steps = abs(dx);
	}
	else
	{
		nb_steps = abs(dy);
	}
	float x_inc = (abs(dx) / nb_steps);
	float y_inc = (abs(dy) / nb_steps);
	int v;
	int x = xa;
	int y = yb;
	for(v=0; v<nb_steps; v++)
	{
		setPixel(x, y);
		x += x_inc;
		y += y_inc;
	}
	glFlush();
	
}



int main(int argc, char** argv)
{
	printf("Enter coordinates : ");
	scanf("%d%d%d%d", &xa, &ya, &xb, &yb);
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutCreateWindow("DDA line");
	glutInitWindowSize(500.0, 500.0);
	glMatrixMode(GL_PROJECTION);
	glutDisplayFunc(display); 
	glutMainLoop();
	return 0;
}



