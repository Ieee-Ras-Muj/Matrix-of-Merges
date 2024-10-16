import os

from PIL import Image, ImageDraw, ImageFont

FONT_FILE = ImageFont.truetype(r"GreatVibes-Regular.ttf", 100)
FONT_COLOR = "#000000"
WIDTH, HEIGHT = 2000, 1414


def make_cert(name):
    image_source = Image.open(r"certificate.png")
    draw = ImageDraw.Draw(image_source)

    name_bbox = draw.textbbox((0, 0), name, font=FONT_FILE)
    name_width = name_bbox[2] - name_bbox[0]
    name_height = name_bbox[3] - name_bbox[1]

    text_x = (WIDTH - name_width) / 2
    text_y = (HEIGHT - name_height) / 2

    draw.text((text_x, text_y), name, fill=FONT_COLOR, font=FONT_FILE)

    output_dir = "./certificates"
    os.makedirs(output_dir, exist_ok=True)

    if image_source.mode == "RGBA":
        image_source = image_source.convert("RGB")

    output_path = os.path.join(output_dir, f"{name}_certificate.jpg")
    image_source.save(output_path, "JPEG")

    print(f"Printing certificate for: {name}")

names = ["Dhruv Kunzru", "Arhant Bafna", "Karan Parashar", "Taksh Modasia"]

for name in names:
    make_cert(name)
