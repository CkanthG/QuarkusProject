CREATE TABLE IF NOT EXISTS Person(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        email VARCHAR(255),
                        phoneNumber VARCHAR(20),
                        city VARCHAR(100)
);
