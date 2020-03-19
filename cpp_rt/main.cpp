#include <iostream>
#include <math.h>
#include <stdlib.h>

class vec3 {
    vec3() {}

    vec3(float e0, float e1, float e2) {
        e[0] = e0;
        e[1] = e1;
        e[2] = e2;
    }

    inline float const x() {
        return e[0];
    }

    inline float const y() {
        return e[1];
    }

    inline float const z() {
        return e[2];
    }

    inline float const r() {
        return e[0];
    }

    inline float const g() {
        return e[1];
    }

    inline float const b() {
        return e[2];
    }

    inline const vec3& operator+() const {
        return *this;
    }

    inline vec3 operator-() const {
        return vec3(-e[0], -e[1], -e[2]);
    }

    inline float operator[](int i) const {
        return e[i];
    }

    inline float& operator[](int i) {
        return e[i];
    }

    inline vec3& operator+=(const vec3 &v2);
    inline vec3& operator-=(const vec3 &v2);
    inline vec3& operator*=(const vec3 &v2);
    inline vec3& operator/=(const vec3 &v2);
    inline vec3& operator*=(const float t);
    inline vec3& operator/=(const float t);

    inline float length() const {
        return sqrt(e[0]*e[0] + e[1]*e[1] + e[2]*e[2]);
    }

    inline float squared_length() const {
        return e[0]*e[0] + e[1]*e[1] + e[2]*e[2];
    }

    friend inline std::istream& operator>>(std::istream &is, vec3 &t) {
        is >> t.e[0] >> t.e[1] >> t.e[2];
        return is;
    }

    friend inline std::ostream& operator<<(std::ostream &os, vec3 &t) {
        os << t.e[0] << " " << t.e[1] << " " << t.e[2];
        return os;
    }

    inline void make_unit_vector();

    inline void vec3::make_unit_vector() {
        float k = 1.0 / sqrt(e[0]*e[0] + e[1]*e[1] + e[2]*e[2]);
        e[0] *= k;
        e[1] *= k;
        e[2] *= k;
    }

    float e[3];
};

int main()
{
    int nx = 200;
    int ny = 100;

    std::cout << "P3\n" << nx << " " << ny << "\n255\n";

    for (int j = ny-1; j >= 0; j--) {
        for (int i = 0; i < nx; i++) {
            float r = float (i) / float (nx);
            float g = float (j) / float (ny);
            float b = 0.2;
            int ir = int(255.99 * r);
            int ig = int(255.99 * g);
            int ib = int(255.99 * b);
            std::cout << ir << " " << ig << " " << ib << "\n";
        }
    }
    
    return 0;
}
