from pathlib import Path
from PIL import Image, ImageDraw, ImageFont


ROOT = Path(__file__).resolve().parent.parent
ASSETS = ROOT / "src" / "rockpaperscissors" / "assets"
DOCS = ROOT / "docs" / "images"
ASSETS.mkdir(parents=True, exist_ok=True)
DOCS.mkdir(parents=True, exist_ok=True)


def font(size: int):
    try:
        return ImageFont.truetype("/System/Library/Fonts/Supplemental/Arial Bold.ttf", size)
    except OSError:
        return ImageFont.load_default()


def centered(draw: ImageDraw.ImageDraw, box, text, fill, fnt):
    x1, y1, x2, y2 = box
    bbox = draw.textbbox((0, 0), text, font=fnt)
    tx = x1 + ((x2 - x1) - (bbox[2] - bbox[0])) // 2
    ty = y1 + ((y2 - y1) - (bbox[3] - bbox[1])) // 2
    draw.text((tx, ty), text, font=fnt, fill=fill)


def choice_icon(filename: str, text: str, color):
    img = Image.new("RGBA", (256, 256), (0, 0, 0, 0))
    d = ImageDraw.Draw(img)
    d.rounded_rectangle((8, 8, 248, 248), radius=40, fill=color, outline=(255, 255, 255), width=4)
    centered(d, (8, 8, 248, 248), text, (255, 255, 255), font(34))
    img.save(ASSETS / filename)


def start_banner():
    img = Image.new("RGBA", (640, 240), (37, 99, 235))
    d = ImageDraw.Draw(img)
    d.rounded_rectangle((0, 0, 639, 239), radius=36, fill=(37, 99, 235), outline=(30, 64, 175), width=3)
    d.rounded_rectangle((24, 30, 616, 210), radius=30, fill=(255, 255, 255))
    centered(d, (24, 54, 616, 130), "ROCK PAPER SCISSORS", (30, 41, 59), font(42))
    centered(d, (24, 130, 616, 195), "Swing Edition", (51, 65, 85), font(26))
    img.save(ASSETS / "rock-paper-scissors.png")


def draw_card(d, x, y, w, h, bg, border, text, text_color):
    d.rounded_rectangle((x, y, x + w, y + h), radius=16, fill=bg, outline=border, width=2)
    centered(d, (x, y, x + w, y + h), text, text_color, font(28))


def draw_button(d, x, y, w, h, text):
    d.rounded_rectangle((x, y, x + w, y + h), radius=14, fill=(37, 99, 235), outline=(15, 23, 42), width=2)
    centered(d, (x, y, x + w, y + h), text, (255, 255, 255), font(30))


def screenshot(filename: str, title: str, dark: bool, active: bool):
    bg = (15, 23, 42) if dark else (245, 247, 250)
    card = (30, 41, 59) if dark else (255, 255, 255)
    text = (226, 232, 240) if dark else (30, 41, 59)
    accent = (96, 165, 250) if dark else (37, 99, 235)

    img = Image.new("RGBA", (1200, 760), bg)
    d = ImageDraw.Draw(img)
    d.rounded_rectangle((40, 40, 1160, 720), radius=24, fill=card)

    centered(d, (60, 56, 1140, 115), "Rock Paper Scissors - Swing UI", accent, font(40))
    draw_card(d, 70, 140, 510, 86, card, (148, 163, 184), f"Oyuncu Skoru: {'3' if active else '0'}", text)
    draw_card(d, 620, 140, 510, 86, card, (148, 163, 184), f"Bot Skoru: {'2' if active else '0'}", text)

    round_text = "Kazandin!" if active else "Oyunu baslatmak icin Start'a tikla"
    draw_card(d, 70, 250, 1060, 70, card, (148, 163, 184), round_text, accent)
    draw_card(d, 70, 340, 510, 220, card, (148, 163, 184), "Oyuncu Secimi", text)
    draw_card(d, 620, 340, 510, 220, card, (148, 163, 184), "Bot Secimi", text)

    centered(d, (70, 430, 580, 510), "ROCK" if active else "?", text, font(30))
    centered(d, (620, 430, 1130, 510), "SCISSORS" if active else "?", text, font(30))

    draw_button(d, 70, 590, 340, 90, "Rock")
    draw_button(d, 430, 590, 340, 90, "Paper")
    draw_button(d, 790, 590, 340, 90, "Scissors")
    centered(d, (50, 716, 1150, 752), title, text, font(20))
    img.save(DOCS / filename)


if __name__ == "__main__":
    choice_icon("rock.png", "ROCK", (71, 85, 105))
    choice_icon("paper.png", "PAPER", (59, 130, 246))
    choice_icon("scissors.png", "SCISSORS", (16, 185, 129))
    start_banner()

    screenshot("home.png", "Baslangic Ekrani", dark=False, active=False)
    screenshot("round-win.png", "Oyun Ani - Oyuncu Kazandi", dark=False, active=True)
    screenshot("dark-theme.png", "Koyu Tema", dark=True, active=True)
