package com.example.authserver.domain.controller;

//
//@RestController
//@CrossOrigin()
//@AllArgsConstructor
//public class MyControllerxx {
//
//    private final QRRepository qrRepository;
//    private final ProfileRepository profileRepository;
//    private final GenerationQRImageService test;
//    private final JwtService tokenService;
//
//    @GetMapping("/test12")
//    public String tet(Authorization ddas) {
//        var cd = new UserEntity();
//        cd.setRoles(new HashSet<>());
//        cd.setEmail("dasdas");
//        return tokenService.generateAccessToken(new CustomUsrDetails(cd), 1L);
//
//    }
//
//    @PostMapping("/upload")
//    public Object uploadFile(@ModelAttribute QRcodeInsertDto dto) throws IOException {
//        // Handle the data and uploaded files here
//        //  HttpHeaders headers = new HttpHeaders();
//        //  headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_JPEG
//        String base64String = Base64.getEncoder().encodeToString(dto.getContents().get(0).getBytes());
//
//        return base64String;
//    }
//
//    @PostMapping(value = "/image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
//    public ResponseEntity<byte[]> testFileds(@RequestParam("mediaData") MultipartFile mediaData) throws IOException, WriterException {
//
//        var type = mediaData.getBytes();
//        var v = new byte[]{1, 2, 3,};
////        var imageBytes= test.generateQRImage(1L);
//
//        String qrCodeValue = "https://www.google.com/?id=10"; //clientUrl+"?qrid="+qrId;
//        var qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeValue, BarcodeFormat.QR_CODE, 700, 700);
//
//        BufferedImage qrCodeImage = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
//        qrCodeImage.createGraphics();
//
//        // Customize the QR code appearance
//        Graphics2D graphics = (Graphics2D) qrCodeImage.getGraphics();
//        graphics.setColor(Color.RED); // Set the background color
//        graphics.fillRect(0, 0, 1000, 1000);
//        graphics.setColor(Color.GREEN); // Set the foreground color
//
//
//        for (int x = 0; x < 700; x++) {
//            for (int y = 0; y < 700; y++) {
//                if (bitMatrix.get(x, y)) {
//                    graphics.fillRect(x, y, 1, 1);
//                }
//            }
//        }
//        // Set the appropriate headers and return the byte array in the ResponseEntity
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG); // or MediaType.IMAGE_JPEG
//        return new ResponseEntity<>(convertBufferImageToByte(qrCodeImage), headers, HttpStatus.OK);
////        profileRepository.initProfile("das", "danila, " ,"kdartuzov");
////        qrRepository.initQrCode(LocalDateTime.now(), "some ",
////                v, "das", 1L);
//    }
//
//
//    private byte[] convertBufferImageToByte(BufferedImage bufferedImage) throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "png", baos);
//        byte[] imageBytes = baos.toByteArray();
//
//        return imageBytes;
//    }
//
//}