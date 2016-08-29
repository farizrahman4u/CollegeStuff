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
	float x = 0;
	float y = yr;
	float xr_sq = xr * xr;
	float yr_sq = yr * yr;
	// Region 1
	float p = yr_sq - xr_sq * yr + 0.25 * xr_sq;
	while(yr_sq * x < xr_sq * y)
	{
		if(p < 0)
		{
			x += 1;
			p += yr_sq * (2 * x + 1);
		}
		else
		{
			x += 1;
			y -= 1;
			p += yr_sq * (2 * x + 1) - 2 * xr_sq * y;
		}
		setPixel(x + xc, y + yc);
		setPixel(x + xc, -y + yc);
		setPixel(-x + xc, y + yc);
		setPixel(-x + xc, -y + yc);

	}
	// Region 2

	p = yr_sq * pow(x + 0.5, 2) + xr_sq * pow(y - 1, 2) - xr_sq * yr_sq;
	while(y)
	{
		if(p > 0)
		{
			y -= 1;
			p += xr_sq * (-2 * y + 1);
		}
		else
		{
			x += 1;
			y -= 1;
			p += 2 * (yr_sq * x - xr_sq * y) + xr_sq;
		}
		setPixel(x + xc, y + yc);
		setPixel(x + xc, -y + yc);
		setPixel(-x + xc, y + yc);
		setPixel(-x + xc, -y + yc);
	}
	glFlush();
	
}



int main(int argc, char** argv)
{
	printf("Enter center coordinates : ");
	scanf("%d%d", &xc, &yc);
	printf("Enter axes lengths : ");
	scanf("%d%d", &xr, &yr);
	glutInit(&argc, argv);
	glutCreateWindow("Midpoint ellipse algorithm");
	glutInitWindowSize(500.0, 500.0);
	glutDisplayFunc(display); 
	glutMainLoop();
	return 0;
}


