class Car {
    private:
    float speed = 0;
    bool isMoving = false;
    public:
    void stop() {
        isMoving = false;
    }
    void accelerate(float amount) {
        speed += amount;
        isMoving = speed != 0;
    }
    void printSpeed() {
        std::cout << "Speed: " << speed << std::endl;
    }
    void printIsMoving() {
        if (isMoving) {
            std::cout << "The car is moving" << std::endl;
        } else {
            std::cout << "The car is not moving" << std::endl;
        }
    }
};