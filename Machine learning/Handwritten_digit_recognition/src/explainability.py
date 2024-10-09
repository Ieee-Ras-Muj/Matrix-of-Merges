# explainability.py
import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt
from model import create_base_model

def grad_cam(input_model, img_array, layer_name):
    # Define a model that maps the input image to the activations of the desired layer
    grad_model = tf.keras.models.Model(
        inputs=[input_model.inputs],
        outputs=[input_model.get_layer(layer_name).output, input_model.output]
    )
    with tf.GradientTape() as tape:
        conv_outputs, predictions = grad_model(img_array)
        loss = predictions[:, tf.argmax(predictions[0])]

    # Compute gradients
    output = conv_outputs[0]
    grads = tape.gradient(loss, conv_outputs)[0]
    gate_f = tf.cast(output > 0, "float32")
    gate_r = tf.cast(grads > 0, "float32")
    guided_grads = gate_f * gate_r * grads

    # Average gradients spatially
    weights = tf.reduce_mean(guided_grads, axis=(0, 1))
    cam = tf.reduce_sum(tf.multiply(weights, output), axis=-1)

    # Process CAM
    cam = np.maximum(cam, 0)
    cam = cam / np.max(cam)
    cam = tf.image.resize(cam, (28, 28))
    return cam

# Test with a sample image
if __name__ == "__main__":
    model = create_base_model()
    img = np.expand_dims(np.random.random((28, 28, 1)), axis=0)
    cam = grad_cam(model, img, 'conv2d')
    plt.imshow(cam, cmap='jet')
    plt.show()
