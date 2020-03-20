#include <iostream>
#include <math.h>
#include <stdlib.h>

class vec3 {
    public:
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

        inline void make_unit_vector();

        float e[3];
};

inline std::istream& operator>>(std::istream& is, vec3& t) {
    is >> t.e[0] >> t.e[1] >> t.e[2];
    return is;
}

inline std::ostream& operator<<(std::ostream& os, vec3& t) {
    os << t.e[0] << " " << t.e[1] << " " << t.e[2];
    return os;
}

inline void vec3::make_unit_vector() {
    float k = 1.0 / sqrt(e[0]*e[0] + e[1]*e[1] + e[2]*e[2]);
    e[0] *= k; e[1] *= k; e[2] *= k;
}

inline vec3 operator+(const vec3& v1, const vec3& v2) {
    return vec3(v1.e[0] + v2.e[0], v1.e[1] + v2.e[1], v1.e[2] + v2.e[2]);
}

inline vec3 operator-(const vec3& v1, const vec3& v2) {
    return vec3(v1.e[0] - v2.e[0], v1.e[1] - v2.e[1], v1.e[2] - v2.e[2]);
}

inline vec3 operator*(const vec3& v1, const vec3& v2) {
    return vec3(v1.e[0] * v2.e[0], v1.e[1] * v2.e[1], v1.e[2] * v2.e[2]);
}

inline vec3 operator*(float t, const vec3& v) {
    return vec3(t*v.e[0], t*v.e[1], t*v.e[2]);
}

inline vec3 operator*(const vec3& v, float t) {
    return vec3(t*v.e[0], t*v.e[1], t*v.e[2]);
}

inline vec3 operator/(const vec3& v1, const vec3& v2) {
    return vec3(v1.e[0] / v2.e[0], v1.e[1] / v2.e[1], v1.e[2] / v2.e[2]);
}

inline vec3 operator/(vec3 v, float t) {
    return vec3(v.e[0] / t, v.e[1] / t, v.e[2] / t);
}

inline float dot(vec3& v1, vec3& v2) {
    return v1.e[0] * v2.e[0] + 
            v1.e[1] * v2.e[1] +
            v1.e[2] * v2.e[2];
}

inline vec3 cross(vec3& v1, vec3& v2) {
    return vec3(v1.e[1] * v2.e[2] - v1.e[2] * v2.e[1],
                v1.e[2] * v2.e[0] - v1.e[0] * v2.e[2],
                v1.e[0] * v2.e[1] - v1.e[1] * v2.e[0]);
}

inline vec3& vec3::operator+=(const vec3& v) {
    e[0] += v.e[0];
    e[1] += v.e[1];
    e[2] += v.e[2];
    return *this;
}

inline vec3& vec3::operator-=(const vec3& v) {
    e[0] -= v.e[0];
    e[1] -= v.e[1];
    e[2] -= v.e[2];
    return *this;
}

inline vec3& vec3::operator*=(const vec3& v) {
    e[0] *= v.e[0];
    e[1] *= v.e[1];
    e[2] *= v.e[2];
    return *this;
}

inline vec3& vec3::operator*=(const float t) {
    e[0] *= t;
    e[1] *= t;
    e[2] *= t;
    return *this;
}

inline vec3& vec3::operator/=(const vec3& v) {
    e[0] /= v.e[0];
    e[1] /= v.e[1];
    e[2] /= v.e[2];
    return *this;
}

inline vec3& vec3::operator/=(const float t) {
    float k = 1.0 / t;
    e[0] *= k;
    e[1] *= k;
    e[2] *= k;
    return *this;
}

inline vec3 unit_vector(vec3 v) {
    return v / v.length();
}

class ray {
    public:
        ray() {}
        
        ray(const vec3& a, const vec3& b) {
            A = a; 
            B = b;
        }

        vec3 origin() const {
            return A;
        }

        vec3 direction() const {
            return B;
        }

        vec3 point_at_parameter(float t) const {
            return A + t*B;
        }

        vec3 A;
        vec3 B;
};

int main()
{
    int nx = 200;
    int ny = 100;

    std::cout << "P3\n" << nx << " " << ny << "\n255\n";

    for (int j = ny-1; j >= 0; j--) {
        for (int i = 0; i < nx; i++) {
            vec3 col(float(i) / float(nx), float(j) / float(ny), 0.2);
            int ir = int(255.99 * col[0]);
            int ig = int(255.99 * col[1]);
            int ib = int(255.99 * col[2]);
            std::cout << ir << " " << ig << " " << ib << "\n";
        }
    }
    
    return 0;
}
