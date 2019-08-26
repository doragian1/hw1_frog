package com.example.froggy;

class User {
        int points;
        int missPoints;
        String name;

        public User(String name) {
            this.points = 0;
            this.missPoints = 0;
            this.name = name;
        }
        public User() {
            this.points = 0;
            this.missPoints = 0;
            this.name = "";
        }

        public int getPoints() {
            return points;
        }

        public int getMissPoints() {
            return missPoints;
        }

        public String getName() {
            return name;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public void setMissPoints(int missPoints) {
            this.missPoints = missPoints;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addPoints(){
            this.points++;
        }

        public void addMiss(){
            this.missPoints++;
        }


}
