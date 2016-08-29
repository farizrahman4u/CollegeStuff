#include <GL/glut.h> 
#include <stdio.h>
#include<math.h>

void setPixel(GLint xc, GLint yc)
{
	glBegin(GL_POINTS);
		glVertex2f((((float)xc - 400) /800), ((float)yc - 400) / 800);
	glEnd();
	glFlush();
}


void dda_line(int xa, int ya, int xb, int yb)
{
	int dx = xb - xa;
	int dy = yb - ya;
	int nb_steps;
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
}



void dda_rect(int x, int y, int w, int h)
{
	int xa = x;
	int ya = y;
	int xb = x;
	int yb = y + w;
	int i;
	for(i=0; i<w; i++)
	{
		dda_line(xa, ya, xb, yb);
		xa += 1;
		xb += 1;
	}
}


void setColor(int c)
{
	if(c)
	{
		glColor3f(1.0f, 1.0f, 1.0f);
	}
	else
	{
		glColor3f(0.0f, 0.0f, 0.0f);
	}
}

void drawBoard()
{
	int i, j;
	for(i=0; i<800; i+=100)
		for(j=0; j<800; j+=100)
		{
			setColor(((i + j) / 100) % 2);
			dda_rect(i, j, 100, 100);
		}
}


void display()
{
	glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
	glClear(GL_COLOR_BUFFER_BIT);
	drawBoard();
	glFlush(); 

}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutCreateWindow("Chess");
	glutInitWindowSize(800.0, 800.0);
	glMatrixMode(GL_PROJECTION);
	glutDisplayFunc(display); 
	glutMainLoop();
	return 0;
}


