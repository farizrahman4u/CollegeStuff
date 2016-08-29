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
	int x_k = rc;
	int y_k = 0;
	float t = 0;
	float dt = 1.0 / rc;
	setPixel(x_k, y_k);
	while(x_k > y_k)
	{
		t += dt;
		x_k = rc * cos(t);
		y_k = rc * sin(t);
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
	glutCreateWindow("Polar circle");
	glutInitWindowSize(500.0, 500.0);
	glutDisplayFunc(display); 
	glutMainLoop();
	return 0;
}


