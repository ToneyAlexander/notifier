from PIL import Image

### python ./app/src/main/res/raw/color_picker_generator.py ###

img = Image.new('HSV', (255,255), "black")
# Create the pixel map
pixels = img.load()
# For every pixel:
for i in range(img.size[0]):
    for j in range(img.size[1]):
        pixels[i,j] = (255-i, 255-j, 255)

img.convert('RGB').save("./color_picker.png")
img.show()