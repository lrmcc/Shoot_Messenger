# Shoot
<font size=4>An end-to-end encrypted messaging platform</font>
---
### Description
"_Shoot me a message_"
- Shoot is an encrypted messaging platform for server instance operators to have complete control of messaging data.
- A server instance is hosted on a computer connected to the internet or in a cloud environment.
- Users are connected to a server by the server instance IP address and port via shoot client application.

### Installation
  #### Method 1 - Manually build executable jar files
  ##### Client
  1. Clone or download the repo
  2. Navigate to the repo directory
  3. Open client project folder: `cd ./app`
  4. Build client application: `gradle build`
  5. Extract the client .zip files from app/build/distributions/app-<version>.zip.
  
  ##### Server
  1. Clone or download the repo
  2. Navigate to the repo directory
  3. Open server project folder: `cd ../server`
  4. Build server application: `gradle build`
  5. Extract the server .zip file from server/build/distributions/server-boot<version>.zip.

  #### Method 2 (Future) - Download executables and verify check sums

### Usage
#### Run a server instance
  1. Launch server executable jar: `java -jar shoot-server-<version>.jar`
  2. Change port settings and user policies if desired. (Future)
  3. Deploy server; IP/Port posted to Shoot server list. (Future)
#### Connect as user
  1. Launch client executable jar: `java -jar shoot-app-<version>.jar`
  2. Login as user. (Future)
  3. Connect to desired server from Shoot server list. (Future)

### Development Roadmap
  - Refactor and shore up basic messaging
  - Add encryption capabilities
  - Clarify useage over internet and LAN
  - Update server to retain and load data
  - User profile associated IP addressess record to retain/block  

### Development Tools
  - Java 17
  - Spring Boot Dependencies
    - Spring Web
    - Spring Session  (Future)
    - Spring for RabbitMQ
    - Spring Session  (Future)
    - Spring Security  (Future)
    - WebSocket (Future)
  - Docker (Future)
  - Github CI/CD (Future)

### License
MIT License

Copyright (c) 2023 Luke McConnell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.