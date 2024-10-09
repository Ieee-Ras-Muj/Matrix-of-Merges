# train.py
import tensorflow as tf
from model import create_base_model
from tensorflow.keras.callbacks import Callback
import numpy as np

class CustomMetricsCallback(Callback):
    def on_epoch_end(self, epoch, logs=None):
        val_loss = logs.get('val_loss')
        val_accuracy = logs.get('val_accuracy')
        print(f"End of epoch {epoch}: Validation loss = {val_loss:.4f}, Validation accuracy = {val_accuracy:.4f}")
        
        # TODO: Save validation loss and accuracy to file for further analysis
        # with open('metrics_log.txt', 'a') as f:
        #     f.write(f"{epoch},{val_loss},{val_accuracy}\n")

def train_model():
    # Load dataset
    (x_train, y_train), (x_test, y_test) = tf.keras.datasets.mnist.load_data()
    x_train, x_test = x_train / 255.0, x_test / 255.0

    # Reshape to match input shape requirements
    x_train = np.expand_dims(x_train, -1)
    x_test = np.expand_dims(x_test, -1)

    # Create and train the model
    model = create_base_model()
    history = model.fit(x_train, y_train, validation_data=(x_test, y_test), epochs=5, callbacks=[CustomMetricsCallback()])

if __name__ == "__main__":
    train_model()
