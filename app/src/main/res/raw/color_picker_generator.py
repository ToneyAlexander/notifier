from PIL import Image

### python ./app/src/main/res/raw/color_picker_generator.py ###

def hslSlice(satL):
    img = Image.new('HSV', (255,255), "black")
    # Create the pixel map
    pixels = img.load()
    # For every pixel:
    for i in range(img.size[0]):
        for j in range(img.size[1]):
            #The loop iterates over HSL
            #This math converts it to HSV
            L = (255-j)/255
            value = L + satL*min(L, 1-L)
            satV = 0
            if(value != 0):
                satV=2*(1-(L/value))
            pixels[i,j] = (255-i, int(255*satV), int(255*value))
    img.show()
    return img

### IMPORTANT CONSTANT ###
satL = 1.0

hslSlice(satL).convert('RGB').save("./app/src/main/res/drawable/color_picker.png")