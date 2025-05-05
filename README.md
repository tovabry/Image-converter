# Image Converter - PNG <-> JPG

A modular Java 24 application that converts images between PNG and JPG formats.  
Use it via Docker or run it locally using Maven.

## Features

- Converts `.png` to `.jpg` and vice versa
- Modular Java 24 project with `module-info.java`
- Built with Maven
- Use Docker to run without installing Java
- Simple folder-based workflow using Docker volumes

## Project Structure

This project follows a modular structure with separate folders for:

- `service` – common interfaces
- `serviceprovider` – implementation of image converters
- `serviceconsumer` – entry point and module runner

JAR files are built to the `runtime` folder during the Maven build.

## Requirements

- Docker (recommended)
- OR Java 24 and Maven for local use

---

## Running with Docker

You can build and run the project using Docker.

Build the Image:

``docker build -t image-converter .``

To run the converter, you need to mount two folders from your local machine:

- One for input images (JPG or PNG)
- One for output (converted images)

Use the following command, replacing the paths with folders on your own system:

Windows:

``
docker run -it ^
  -v "C:/Users/YourName/Pictures/input:/app/input" ^
  -v "C:/Users/YourName/Pictures/output:/app/output" ^
  tovabryngelsson/image-converter
``

macOS/Linux:

``
docker run -it \
  -v "$HOME/Pictures/input:/app/input" \
  -v "$HOME/Pictures/output:/app/output" \
  tovabryngelsson/image-converter
``

Note:

- Make sure the input/output folders exist before running the command.

- The converter scans /app/input for .png and .jpg files and writes the converted ones to /app/output.
