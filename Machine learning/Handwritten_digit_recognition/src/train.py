import numpy as np
import tensorflow as tf
from tensorflow.keras.datasets import mnist
from model import build_model

# Load MNIST data
(x_train, y_train), (x_test, y_test) = mnist.load_data()
x_train, x_test = x_train / 255.0, x_test / 255.0  # Normalize data

# Reshape for the model
x_train = np.expand_dims(x_train, -1)
x_test = np.expand_dims(x_test, -1)

# Build and train the model
model = build_model()
model.fit(x_train, y_train, epochs=10, validation_data=(x_test, y_test))

# Save the model
model.save("mnist_cnn_model.h5")

