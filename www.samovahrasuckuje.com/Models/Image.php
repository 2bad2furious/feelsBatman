<?php

/**
 * Reprezentuje obrázek, který je možné editovat
 * @package system
 */
class Image
{
    /**
     * Typ obrázku PNG
     */
    const TYPE_PNG = IMAGETYPE_PNG;
    /**
     * Typ obrázku JPEG
     */
    const TYPE_JPEG = IMAGETYPE_JPEG;
    /**
     * Typ obrázku GIF
     */
    const TYPE_GIF = IMAGETYPE_GIF;
    /**
     * @var mixed Obrázek
     */
    private $image;
    /**
     * @var int Typ obrázku
     */
    private $image_type;

    /**
     * Vytvoří obrázek ze souboru
     * @param string $filename Cesta k obrázku
     * @param bool $trueColorGif Zda má být formát gif převedený na truecolor
     */
    public function __construct($filename, $trueColorGif = false)
    {
        $image_info = getimagesize($filename);
        $imageType = $image_info[2];
        $this->image_type = $imageType;
        if ($imageType == IMAGETYPE_JPEG)
            $this->image = imagecreatefromjpeg($filename);
        elseif ($imageType == IMAGETYPE_GIF)
        {
            $this->image = imagecreatefromgif($filename);
            // Překopírování obrázku, aby měl paletu truecolor - Kdo dnes bude ukládat jako gif, že
            if ($trueColorGif)
            {
                $trueImage = imagecreatetruecolor($imageType[0], $imageType[1]);
                imagecopy($trueImage, $this->image, 0, 0, 0, 0, $imageType[0], $imageType[1]);
                $this->image = $trueImage;
            }
        }
        elseif ($imageType == IMAGETYPE_PNG)
        {
            $this->image = imagecreatefrompng($filename);
            imagealphablending($this->image, true); // Alfaprůhlednost
            imagesavealpha($this->image, true);
        }
    }

    /**
     * Uloží obrázek
     * @param string $filename Název souboru nebo null pokud se má obrázek vypsat
     * @param int $image_type Typ obrázku
     * @param int $compression Komprese JPEG obrázku (0-100)
     * @param string $permissions Oktanová práva
     */
    public function save($filename = null, $image_type = IMAGETYPE_JPEG, $compression = 85, $permissions = null)
    {
        if ($image_type == IMAGETYPE_JPEG)
            imagejpeg($this->image, $filename, $compression);
        elseif ($image_type == IMAGETYPE_GIF)
            imagegif($this->image, $filename);
        elseif ($image_type == IMAGETYPE_PNG)
            imagepng($this->image, $filename);
        if ($permissions != null)
            chmod($filename, $permissions);
    }

    /**
     * Přidá na obrázek vodoznak
     * @param string $path Cesta k vodoznaku
     */
    public function addWatermark($path)
    {
        $watermark = imagecreatefrompng($path);
        $sx = imagesx($watermark);
        $sy = imagesy($watermark);
        imagecopy($this->image, $watermark, imagesx($this->image) - $sx - 8, imagesy($this->image) - $sy - 6, 0, 0, $sx, $sy);
    }

    /**
     * Vrátí typ obrázku
     * @return int Typ Obrázku
     */
    public function getImageType()
    {
        return $this->image_type;
    }

    /**
     * Vrátí šířku obrázku v pixelech
     * @return int Šířka obrázku
     */
    public function getWidth()
    {
        return imagesx($this->image);
    }

    /**
     * Vrátí výšku obrázku v pixelech
     * @return int Výška obrázku
     */
    public function getHeight()
    {
        return imagesy($this->image);
    }

    /**
     * Zmenší obrázek v poměru tak, aby se vešel do čtverce s danou hranou
     * @param int $edge Hrana čtverce
     * @param bool $every Zda má změnit obrázek i když je menší než $edge
     * @return bool Zda byl obrázek zmenšený či nikoli
     */
    public function resizeToEdge($edge, $every = false)
    {
        $width = $this->getWidth();
        $height = $this->getHeight();
        if (($width <= $edge) && ($height <= $edge) && (!$every))
            return false;
        if ($width > $height)
            $this->resizeToWidth($edge);
        else
            $this->resizeToHeight($edge);
        return true;
    }

    /**
     * Škáluje obrázek v poměru tak, aby měl požadovanou výšku v pixelech
     * @param int $height Výška
     */
    public function resizeToHeight($height)
    {
        $ratio = $height / $this->getHeight();
        $width = $this->getWidth() * $ratio;
        $this->resize($width, $height);
    }

    /**
     * Škáluje obrázek v poměru tak, aby měl požadovanou šířku v pixelech
     * @param int $width Šířka
     */
    public function resizeToWidth($width)
    {
        $ratio = $width / $this->getWidth();
        $height = $this->getHeight() * $ratio;
        $this->resize($width, $height);
    }

    /**
     * Škáluje obrázek v poměru tak, aby měl požadovanou velikost v procentech oproti originálu
     * @param int $scale Velikost v procentech
     */
    public function scale($scale)
    {
        $width = $this->getWidth() * $scale / 100;
        $height = $this->getHeight() * $scale / 100;
        $this->resize($width, $height);
    }

    /**
     * Ořízne obrázek na určitou šířku a výšku v pixelech
     * @param int $width Šířka
     * @param int $height Výška
     */
    public function crop($width, $height)
    {
        $image = imagecreatetruecolor($width, $height);
        $transparent = imagecolorallocatealpha($image, 0, 0, 0, 127);
        imagefill($image, 0, 0, $transparent);
        imagecopy($image, $this->image, 0, 0, 0, 0, $width, $height);
        imagesavealpha($image, true);
        $this->image = $image;
    }

    /**
     * Změní velikost obrázku
     * @param int $width Šířka v pixelech
     * @param int $height Výška v pixelech
     */
    public function resize($width, $height)
    {
        $image = imagecreatetruecolor($width, $height);
        imagealphablending($image, true);
        $transparent = imagecolorallocatealpha($image, 0, 0, 0, 127);
        imagefill($image, 0, 0, $transparent);
        imagecopyresampled($image, $this->image, 0, 0, 0, 0, $width, $height, $this->getWidth(), $this->getHeight());
        imagesavealpha($image, true);
        $this->image = $image;
    }


    /**
     * WTF hack z PHP manuálu, který opravuje fce imagecopymerge, aby uměla alfaprůhlenost
     * @param $dst_im
     * @param $src_im
     * @param $dst_x
     * @param $dst_y
     * @param $src_x
     * @param $src_y
     * @param $src_w
     * @param $src_h
     * @param $pct
     * @return bool
     */
    private function imagecopymerge_alpha($dst_im, $src_im, $dst_x, $dst_y, $src_x, $src_y, $src_w, $src_h, $pct)
    {
        if (!isset($pct))
            return false;
        $pct /= 100;
        // Get image width and height
        $w = imagesx($src_im);
        $h = imagesy($src_im);
        // Turn alpha blending off
        imagealphablending($src_im, false);
        // Find the most opaque pixel in the image (the one with the smallest alpha value)
        $minalpha = 127;
        for ($x = 0; $x < $w; $x++)
            for ($y = 0; $y < $h; $y++)
            {
                $alpha = (imagecolorat($src_im, $x, $y) >> 24) & 0xFF;
                if ($alpha < $minalpha)
                {
                    $minalpha = $alpha;
                }
            }
        //loop through image pixels and modify alpha for each
        for ($x = 0; $x < $w; $x++)
        {
            for ($y = 0; $y < $h; $y++)
            {
                //get current alpha value (represents the TANSPARENCY!)
                $colorxy = imagecolorat($src_im, $x, $y);
                $alpha = ($colorxy >> 24) & 0xFF;
                //calculate new alpha
                if ($minalpha !== 127)
                {
                    $alpha = 127 + 127 * $pct * ($alpha - 127) / (127 - $minalpha);
                }
                else
                {
                    $alpha += 127 * $pct;
                }
                //get the color index with new alpha
                $alphacolorxy = imagecolorallocatealpha($src_im, ($colorxy >> 16) & 0xFF, ($colorxy >> 8) & 0xFF, $colorxy & 0xFF, $alpha);
                //set pixel with the new color + opacity
                if (!imagesetpixel($src_im, $x, $y, $alphacolorxy))
                {
                    return false;
                }
            }
        }
        // The image copy
        imagecopy($dst_im, $src_im, $dst_x, $dst_y, $src_x, $src_y, $src_w, $src_h);
        return true;
    }


    /**
     * Přidá obrázku odraz
     * @param int $reflectionHeight Výška odrazu
     * @param int $startingTransparency Počáteční průhlednost
     */
    public function reflect($reflectionHeight, $startingTransparency)
    {
        $width = $this->getWidth();
        $height = $this->getHeight();
        // celé pozadí
        $bg = imagecreatetruecolor($width, $height + $reflectionHeight);
        imagealphablending($bg, true);
        $transparent = imagecolorallocatealpha($bg, 0, 0, 0, 127);
        imagefill($bg, 0, 0, $transparent);
        // odraz
        // otočení
        $rotated = imagerotate($this->image, -180, imagecolorallocate($this->image, 255, 255, 255));
        $reflection = imagecreatetruecolor($width, $height + $reflectionHeight);
        imagealphablending($reflection, true);
        $transparent = imagecolorallocatealpha($reflection, 0, 0, 0, 127);
        imagefill($reflection, 0, 0, $transparent);
        // převrácení
        for ($x = 0; $x < $width; $x++)
        {
            imagecopy($reflection, $rotated, $x, 0, $width - $x - 1, 0, 1, $reflectionHeight);
        }
        imagesavealpha($reflection, true);
        // přidání obrázku
        imagecopy($bg, $this->image, 0, 0, 0, 0, $width, $height);
        // přidání odrazu
        $in = $startingTransparency / $reflectionHeight;
        for ($i = 0; $i <= $reflectionHeight; $i++)
        {
            if ($startingTransparency < 0)
                $startingTransparency = 0;
            $this->imagecopymerge_alpha($bg, $reflection, 0, $height + $i, 0, $i, $width, 1, $startingTransparency);
            $startingTransparency -= $in;
        }
        imagesavealpha($bg, true);
        $this->image = $bg;
    }

    /**
     * Zjistí zda je daný soubor obrázek
     * @param string $fileName Soubor
     * @return bool Zda je soubor obrázek
     */
    public static function isImage($fileName)
    {
        $type = exif_imagetype($fileName);
        return (($type == IMAGETYPE_JPEG) || ($type == IMAGETYPE_GIF) || ($type == IMAGETYPE_PNG));
    }
}