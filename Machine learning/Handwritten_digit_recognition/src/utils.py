import numpy as np

def preprocess_data(x):
    """Normalize and reshape data for the CNN model."""
    x = x / 255.0  # Normalize
    return np.expand_dims(x, -1)  # Add a channel dimension

