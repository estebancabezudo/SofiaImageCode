# Image CND and SIC
## What's an image CDN?
Image CDN specialize in the transformation, optimization, and delivery of images. You can also think of them as APIs for accessing and manipulating the images used on your site.

## Why use an image CDN?
Image content delivery networks (CDNs) are excellent at optimizing images.

## What's SIC?
SIC stand for Sofia Image Code. Is a functional language for WS API image manipulation.

## How does it work?
You have one image upladed on the server. In the string query, you manipulate it with SIC in order to get a diferent image. For responsive sites, you upload one image, a big image, and manipulete it in order to get a diferent image accord your device, for example smaller.

## How do you do that? Just add the SIC code in the URL query string. The next URL is an example of image with some code to manipulate the result.

```http://manager:8080/images/test.jpg?resize(width=300,height=200)```

The original image has 1200 pixels width and 800 pixel height, but now, is smaller and can be used in a movile devices with a data connection.

## SIC grammar
The SIC code has a very simple grammar:

```
code:
  function

parameter:
  name = value

name:
  string

value:
  string
  number
  percentage

string
  any character

number
  integer
  decimal

decimal
  digit . digit(op) e(op) exponent signedInteger

exponent :: one of
  e E

signedInteger
  decimal
  + decimal
  - decimal

integer
 digit

digit :: one of
  0 1 2 3 4 5 6 7 8 9

function:
  string ( )
  string ( parameterOrFunction )

parameterOrFunction:
  parameter
  function
  parameter , parameterOfFunction
  function , parameterOrFunction
```
The spaces don't have any effect in the code therefore you can't use file names with spaces.

## First program
The follow is an example of the full SIC code we can compile it in the SIC web editor. The code is used to resize the image.

```main(loadImage(name=/images/test.jpg),resize(width=300,height=200))```

Note that using the CDN server, some code disapear and is addeded later by the server. In that way the URL is more clean and small.

### Code explained
The code start with a function `main()` where we put all the stuff. Inside this function we going to specify the tasks. The first parameter a is function `loadImage()`. This function load an image in order to have something to manipulate. Is the first task because SIC language work like filters. The first parameter do something with an image an pass image to the input of the next parameter. The parameters for the `main` function are almost all functions.

So, our program first load an image an the result of the load is passed to the `resize()` function. The `resize` function take two parameter in this case `width` and `height` used to specify the file size of the image.

### Use code files
Is posible use a file with code in order to manipulate the image. The URL is more clear if we use code files.

## General functions
```
main()
script()
loadImage()
```
Load an image from the hard disk using the default base path defined in the server or code. This function take only one parameter `name` for the image path.

`name` - The path of the image to load. For web images, the base path is defined in the server configuration and the request path for the image. If you are using a script, the base path is the path defined in the server configuration. When the code is using in an application you can define any base path.

## Transformation functions
```
brightness()
gray()
resize()
```
### brightness()

The brightness function change the brightness of the image. Accept two parameter, one optional for the model used to change the bright and a value that depends on the model used.

`value` - value for the bright change.

`model` - the model use for the bright change algorithm. The default model is the tv model. You can use:

`hsb` - for HSB color space algorithm. The values for this model are from 0 to 3000

`tv` - for tv based algorithm. The values for this model are from -255 to 255.

#### Example
```brightness(value=200,model=tv)```

### gray()
Convert the image to grayscale. This function cat take four parameters: `method`, `type`, `value`, and `channel`.

`method` - You can use six method for grayscale conversion: averaging, luma, desaturation, decomposition, colorChannel, and grayShades. The difference between this method is beyond this tutorial. Feel free to check it in some image conversion site for more information.

`averaging`, `desaturation`, and `grayShades` don't take parameters, but the others methods take different parameters:

`luma` - use a parameter type to specify the type of conversion. Can be bt601, basic, bt709, or bt601.

`decomposition` - use a parameter type to specify the type of conversion. Can be maximum, or minimum.

`colorChannel` - use a parameter channel to specify the channel to use. Can be red, green, or blue.

`grayShades` - use a parameter value to specify the number of shades to use. This value must be between 2-255.

#### Example
```
gray(method=averaging)
gray(method=luma,type=bt709)
gray(method=colorChannel,channel=red)
gray(method=grayShades,value=7)
```
### resize()
Change the size of image. This function cat take four parameters: `width`, `height`, `scale`, and `aspect`.

`width` - An integer with the final width size of the image or a percentage value.

`height` - An integer with the final height size of the image or a percentage value.

`scale` - A decimal value or percentage to specify the scale for the image.

`aspect` - A decimal value or aspect ratio in the form w:h.

You can specify the `width` or `height` in absolute pixels or percentages relatives to the original image. If you specify only `width`, `height`, or `scale`, the new image maintains the original image aspect ratio. You can specify both `width` and `height` or the aspect parameter along width and height in order to change the proportions.

#### Example
```
resize(width=300)
resize(width=300,height=300)
resize(scale=5%)
```
