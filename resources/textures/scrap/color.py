import pygame

tex = "fontthingal2.png"
bannedColor = (0, 0, 0)

texture = pygame.image.load(tex)
pixel = pygame.Surface((1, 1))

newTexture = pygame.Surface((texture.get_width(), texture.get_height()))

for y in range(texture.get_height()):
    for x in range(texture.get_width()):
        fullColor = texture.get_at((x, y))
        color = (fullColor[0], fullColor[1], fullColor[2])
        pixel.fill(color)
        if color == bannedColor:
            pixel.fill((255, 255, 255))
        newTexture.blit(pixel, (x,y))
        print(color)
newTexture.set_colorkey()
pygame.image.save(newTexture, "fontthingal2.png")
