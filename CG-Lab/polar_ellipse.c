#include <GL/glut.h> 
#include <stdio.h>
#include<math.h>


int xr;
int yr;
int xc;
int yc;


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
	float t = 0;
	float dt = 0.01;
	float x = xr;
	float y = 0;
	setPixel(x + xc, y + yc);
	while(t <= 3.14 * 0.5)
	{
		t += dt;
		x = xr * cos(t);
		y = yr * sin(t);
		setPixel(x + xc, y + yc);
		setPixel(x + xc, -y + yc);
		setPixel(-x + xc, y + yc);
		setPixel(-x + xc, -y + yc);
	}
	
}



int main(int argc, char** argv)
{
	printf("Enter center coordinates : ");
	scanf("%d%d", &xc, &yc);
	printf("Enter axes lengths : ");
	scanf("%d%d", &xr, &yr);
	glutInit(&argc, argv);
	glutCreateWindow("Polar ellipse algorithm");
	glutInitWindowSize(500.0, 500.0);
	glutDisplayFunc(display); 
	glutMainLoop();
	return 0;
}


