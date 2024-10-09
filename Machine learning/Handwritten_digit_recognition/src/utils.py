# utils.py
import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator

def basic_augmentation():
    datagen = ImageDataGenerator(
        rotation_range=10,
        width_shift_range=0.1,
        height_shift_range=0.1,
        shear_range=0.1,
        zoom_range=0.1,
        horizontal_flip=False,
        fill_mode='nearest'
    )
    return datagen

def advanced_augmentation():
    datagen = ImageDataGenerator(
        rotation_range=20,
        width_shift_range=0.2,
        height_shift_range=0.2,
        shear_range=0.15,
        zoom_range=0.15,
        horizontal_flip=True,  # TODO: Test with MNIST - does horizontal flip make sense?
        brightness_range=[0.8, 1.2],
        fill_mode='nearest'
    )
    return datagen

# Example usage
if __name__ == "__main__":
    (x_train, y_train), (x_test, y_test) = tf.keras.datasets.mnist.load_data()
    x_train = x_train.reshape(-1, 28, 28, 1)

    # Generate samples and plot
    datagen = advanced_augmentation()
    it = datagen.flow(x_train[:9], batch_size=1)
    for i in range(9):
        batch = it.next()
        print(f"Sample {i+1} augmented shape: {batch.shape}")
