# model.py
import tensorflow as tf
from tensorflow.keras import layers, models

def create_base_model(input_shape=(28, 28, 1), num_classes=10):
    model = models.Sequential()
    
    # Basic Convolutional Layers
    model.add(layers.Conv2D(32, (3, 3), activation='relu', input_shape=input_shape))
    model.add(layers.MaxPooling2D((2, 2)))
    model.add(layers.Conv2D(64, (3, 3), activation='relu'))
    model.add(layers.MaxPooling2D((2, 2)))
    model.add(layers.Conv2D(64, (3, 3), activation='relu'))
    
    # Flatten and Dense Layers
    model.add(layers.Flatten())
    model.add(layers.Dense(128, activation='relu'))  # TODO: Add Dropout layer here for regularization
    model.add(layers.Dense(num_classes, activation='softmax'))

    # Compile the Model
    model.compile(optimizer='adam',
                  loss='sparse_categorical_crossentropy',
                  metrics=['accuracy'])
    return model

# For testing purposes
if __name__ == "__main__":
    model = create_base_model()
    model.summary()
