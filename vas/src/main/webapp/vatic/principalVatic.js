  "use strict";

  let config = {
      // Should be higher than real FPS to not skip real frames
      // Hardcoded due to JS limitations
      fps: 30,

      // Low rate decreases the chance of losing frames with poor browser performances
      playbackRate: 0.4,

      // Format of the extracted frames
      imageMimeType: 'image/jpeg',
      imageExtension: '.jpg',

  };

  let doodle = document.querySelector('#doodle');
  let canvas = document.querySelector('#canvas');
  let ctx = canvas.getContext('2d');
  //let videoFileBtn = document.getElementById('frm:videoFile');
  let videoFile = document.getElementById('frm:videoFile_input');
  let videoDimensionsElement = document.querySelector('#videoDimensions');
  let extractionProgressElement = document.querySelector('#extractionProgress');
  let playButton = document.querySelector('#play');
  let pauseButton = document.querySelector('#pause');
  let selectButton = document.querySelector('#select');
  let viewButton = document.querySelector('tbody tr');
  let speedInput = document.querySelector('#speed');
  let sliderElement = document.querySelector('#slider');
  let extractCoordButton = document.getElementById('frm:accordion:extractCoord');
  
  let framesManager = new FramesManager();
  let annotatedObjectsTracker = new AnnotatedObjectsTracker(framesManager);

  let maxBBox = 0;

  let slider = {
      init: function(min, max, onChange) {
          $(sliderElement).slider('option', 'min', min);
          $(sliderElement).slider('option', 'max', max);
          $(sliderElement).on('slidestop', (e, ui) => {
              onChange(ui.value);
          });
          $(sliderElement).slider('enable');
      },
      setPosition: function(frameNumber) {
          $(sliderElement).slider('option', 'value', frameNumber);
      },
      reset: function() {
          $(sliderElement).slider({ disabled: true });
      }
  };

  slider.reset();

  let player = {
      currentFrame: 0,
      isPlaying: false,
      isReady: false,
      timeout: null,

      initialize: function() {
          this.currentFrame = 0;
          this.isPlaying = false;
          this.isReady = false;

          playButton.disabled = true;
          playButton.style.display = 'block';
          selectButton.disabled = false;
          selectButton.style.display = 'block';
          pauseButton.disabled = true;
          pauseButton.style.display = 'none';
      },

      ready: function() {
          this.isReady = true;

          playButton.disabled = false;
          selectButton.disabled = false;
      },

      seek: function(frameNumber) {
          if (!this.isReady) {
              return;
          }

          this.pause();

          if (frameNumber >= 0 && frameNumber < framesManager.frames.totalFrames()) {
              this.drawFrame(frameNumber);
              this.currentFrame = frameNumber;
          }
      },

      play: function() {
    	  console.log(this.ready);
          if (!this.isReady) {
              return;
          }

          this.isPlaying = true;

          playButton.disabled = true;
          playButton.style.display = 'none';
          selectButton.disabled = false;
          selectButton.style.display = 'block';
          pauseButton.disabled = false;
          pauseButton.style.display = 'block';

          this.nextFrame();
      },

      pause: function() {
          if (!this.isReady) {
              return;
          }

          this.isPlaying = false;
          if (this.timeout != null) {
              clearTimeout(this.timeout);
              this.timeout = null;
          }

          pauseButton.disabled = true;
          pauseButton.style.display = 'none';
          playButton.disabled = false;
          playButton.style.display = 'block';
          selectButton.disabled = false;
          selectButton.style.display = 'block';
      },

      toogle: function() {
          if (!this.isPlaying) {
              this.play();
          } else {
              this.pause();
          }
      },

      nextFrame: function() {
          if (!this.isPlaying) {
              return;
          }

          if (this.currentFrame >= framesManager.frames.totalFrames()) {
              this.done();
              return;
          }

          this.drawFrame(this.currentFrame).then(() => {
              this.currentFrame++;
              //this.timeout = setTimeout(() => this.nextFrame(), 1000 / (config.fps * parseFloat(speedInput.value)));
              this.timeout = setTimeout(() => this.nextFrame(), 1000 / (config.fps * parseFloat("1.00")));

          });
      },

      drawFrame: function(frameNumber) {
          return new Promise((resolve, _) => {
              annotatedObjectsTracker.getFrameWithObjects(frameNumber).then((frameWithObjects) => {
                  ctx.drawImage(frameWithObjects.img, 0, 0);

                  for (let i = 0; i < frameWithObjects.objects.length; i++) {
                      let object = frameWithObjects.objects[i];
                      let annotatedObject = object.annotatedObject;
                      let annotatedFrame = object.annotatedFrame;
                      if (annotatedFrame.isVisible()) {
                          annotatedObject.dom.style.display = 'block';
                          annotatedObject.dom.style.width = annotatedFrame.bbox.width + 'px';
                          annotatedObject.dom.style.height = annotatedFrame.bbox.height + 'px';
                          annotatedObject.dom.style.left = annotatedFrame.bbox.x + 'px';
                          annotatedObject.dom.style.top = annotatedFrame.bbox.y + 'px';
                          annotatedObject.visible.prop('checked', true);
                      } else {
                          annotatedObject.dom.style.display = 'none';
                          annotatedObject.visible.prop('checked', false);
                      }
                  }

                  let shouldHideOthers = frameWithObjects.objects.some(o => o.annotatedObject.hideOthers);
                  if (shouldHideOthers) {
                      for (let i = 0; i < frameWithObjects.objects.length; i++) {
                          let object = frameWithObjects.objects[i];
                          let annotatedObject = object.annotatedObject;
                          if (!annotatedObject.hideOthers) {
                              annotatedObject.dom.style.display = 'none';
                          }
                      }
                  }

                  slider.setPosition(this.currentFrame);

                  resolve();
              });
          });
      },

      done: function() {
          this.currentFrame = 0;
          this.isPlaying = false;

          playButton.disabled = false;
          playButton.style.display = 'block';
          selectButton.disabled = false;
          selectButton.style.display = 'block';
          pauseButton.disabled = true;
          pauseButton.style.display = 'none';
      }
  };

  function clearAllAnnotatedObjects() {
      for (let i = 0; i < annotatedObjectsTracker.annotatedObjects.length; i++) {
          clearAnnotatedObject(i);
      }
  }

  function clearAnnotatedObject(i) {
      let annotatedObject = annotatedObjectsTracker.annotatedObjects[i];
      annotatedObject.controls.remove();
      $(annotatedObject.dom).remove();
      annotatedObjectsTracker.annotatedObjects.splice(i, 1);
  }
  
  //videoFileBtn.addEventListener('click', onFileUploaded, false);
  //videoFile.addEventListener('click', onFileUploaded, false);
  playButton.addEventListener('click', playClicked, false);
  pauseButton.addEventListener('click', pauseClicked, false);
  selectButton.addEventListener('click', selectClicked, false);
  //viewButton.addEventListener('click', extractionFileUploaded, false);
  //extractCoordButton.addEventListener('click', extractCoord, false);

  function playClicked() {
      player.play();
  }

  function pauseClicked() {
      player.pause();
  }

  function selectClicked() {
      if (doodle.style.cursor != 'crosshair') {
          doodle.style.cursor = 'crosshair'
          selectButton.value = 'Dejar de Seleccionar'
      } else {
          doodle.style.cursor = 'default';
          selectButton.value = 'Seleccionar'
      }
  }

  function initializeCanvasDimensions(img) {
	  let wWidth = window.innerWidth; // Obtener el width de la ventana
	  let maxWidth = (wWidth * 0.66); // máximo width para canvas
	  let ratio = 0;
	  let width = img.width
	  let height = img.height;
	  
	  // Calcular nuevos width y height respetando aspect ratio
	  if (width > maxWidth) {
		  ratio = maxWidth / width;
		  width = (width * ratio) - 114;
		  height = height * ratio;
	  }
	  
      doodle.style.width = width + 'px';
      doodle.style.height = height + 'px';
      canvas.width = width;
      canvas.height = height;
      sliderElement.style.width = width + 'px';
  }


  function addFileToExtract(src) {
      var file = new File([0], src, {
          type: "text/plain",
      });
  }

  function loadVideoFromURL(src) {
      addFileToExtract(src);

      extractionFileUploaded();
  }

  
  /* Fila seleccionada de la tabla-lista de videos */
  function onFileSelected(videoURL) {
	  // OJO! Esto pareciera ser un contexto totalmente a parte
	  // Obtener los elementos necesarios
	  doodle = document.getElementById('doodle');
	  canvas = document.getElementById('canvas');
	  ctx = canvas.getContext('2d');
	  videoDimensionsElement = document.getElementById('videoDimensions');
	  extractionProgressElement = document.getElementById('extractionProgress');
	  sliderElement = document.getElementById('slider');
	  playButton = document.getElementById('play');
	  pauseButton = document.getElementById('pause');
	  selectButton = document.getElementById('select');
	  
	  // Adicionar listeners
	  playButton.addEventListener('click', playClicked, false);
	  pauseButton.addEventListener('click', pauseClicked, false);
	  selectButton.addEventListener('click', selectClicked, false);	  
	  
	  // Cargar video en el canvas haciendo fetch
	  extractionFileUploaded2(videoURL);
  }
  
  function onFileUploaded() {
	  console.log("onFileUploaded event!: ", this);
	  /*console.log("file: " + videoFile);
	  var fileData = videoFile.split(",");
	  console.log("data: ", fileData);
	  var file = new File(
			  [fileData[2]], 
			  fileData[0], 
			  { type: fileData[1] }
	  )
	  console.log(file);*/
	  //extractionFileUploaded();
  }


  function extractionFileUploaded2(elem) {
      document.getElementById('frm:accordion:posX').value = null;
      document.getElementById('frm:accordion:posY').value = null;
      document.getElementById('frm:accordion:posX2').value = null;
      document.getElementById('frm:accordion:posY2').value = null;
      
      console.log('blob fetch start!!!');
      fetch(elem).then(res => res.blob()).then(blob => {
    	  // Gets the response and returns it as a blob
          // Here's where you get access to the blob
          // And you can use it for whatever you want
          // Like calling ref().put(blob)
    	  console.log("Got blob: ", blob);
          // Here, I use it to make an image appear on the page
          maxBBox = 0;
          videoFile.disabled = true;
          extractCoordButton.disabled = true;
          clearAllAnnotatedObjects();
          slider.reset();
          player.initialize();
              
          let dimensionsInitialized = false;

          let promise = extractFramesFromVideo(config, blob, (percentage, framesSoFar, lastFrameBlob) => {
        	  blobToImage(lastFrameBlob).then((img) => {
        		  if (!dimensionsInitialized) {
        			  dimensionsInitialized = true;
                      initializeCanvasDimensions(img);
                  }
                  ctx.drawImage(img, 0, 0, img.width, img.height,
                		  			 0, 0, canvas.width, canvas.height);

                  videoDimensionsElement.innerHTML = 'Dimensiones del video: ' + 
                  									 img.width + 
                  									 'x' + 
                  									 img.height;
                  extractionProgressElement.innerHTML = (percentage * 100).toFixed(2) + 
                  										' % completado. ' + 
                  										framesSoFar + 
                  										' frames extraídos.';
              });
          });

          promise.then((frames) => {
              extractionProgressElement.innerHTML = 'Extracción completada. ' + 
              										frames.totalFrames() + 
              										' frames capturados.';
              if (frames.totalFrames() > 0) {
            	  frames.getFrame(0).then((blob) => {
                      blobToImage(blob).then((img) => {
                          initializeCanvasDimensions(img);                          
                          ctx.drawImage(img, 0, 0, img.width, img.height,
                        		             0, 0, canvas.width, canvas.height);
                          videoDimensionsElement.innerHTML = 'Dimensiones del video: ' + 
                          									 img.width + 
                          									 'x' + 
                          									 img.height;

                          framesManager.set(frames);
                          slider.init(
                        		  0, 
                        		  framesManager.frames.totalFrames() - 1, 
                        		  (frameNumber) => player.seek(frameNumber)
                          );
                          
                          player.ready();

                          playButton.disabled = false;
                          selectButton.disabled = false;
                          extractCoordButton.disabled = false;
                      });
                  });
              }
          });
          videoFile.disabled = false;
      });
  }


  function extractionFileUploaded() {

      document.getElementById('frm:accordion:posX').value = null;
      document.getElementById('frm:accordion:posY').value = null;
      document.getElementById('frm:accordion:posX2').value = null;
      document.getElementById('frm:accordion:posY2').value = null;
      
      console.log(this.files);
      if (this.files.length != 1) {
          return;
      }

      maxBBox = 0;
      videoFile.disabled = true;
      extractCoordButton.disabled = true;
      clearAllAnnotatedObjects();
      slider.reset();
      player.initialize();
      
      console.log("canvas width: ", canvas.width);
      
      let promise;
      if (this == videoFile) { 
          let dimensionsInitialized = false;

          promise = extractFramesFromVideo(
              config,
              this.files[0],
              (percentage, framesSoFar, lastFrameBlob) => {
                  blobToImage(lastFrameBlob).then((img) => {
                      if (!dimensionsInitialized) {
                          dimensionsInitialized = true;
                          initializeCanvasDimensions(img);
                      }
                      ctx.drawImage(img, 0, 0, img.width, img.height,
                    		  			 0, 0, canvas.width, canvas.height);

                      videoDimensionsElement.innerHTML = 'Dimensiones del video: ' + img.width + 'x' + img.height;
                      extractionProgressElement.innerHTML = (percentage * 100).toFixed(2) + ' % completado. ' + framesSoFar + ' frames extraídos.';
                  });
              });
      }

      promise.then((frames) => {          
          if (frames.totalFrames() > 0) {
              frames.getFrame(0).then((blob) => {
                  blobToImage(blob).then((img) => {
                	  extractionProgressElement.innerHTML = 'Extracción completada. ' + frames.totalFrames() + ' frames capturados.';
                      initializeCanvasDimensions(img);
                      ctx.drawImage(img, 0, 0, img.width, img.height,
                    		  			 0, 0, canvas.width, canvas.height);
                      videoDimensionsElement.innerHTML = 'Dimensiones del video: ' + img.width + 'x' + img.height;

                      framesManager.set(frames);
                      slider.init(0, framesManager.frames.totalFrames() - 1, (frameNumber) => player.seek(frameNumber));
                      player.ready();

                      playButton.disabled = false;
                      selectButton.disabled = false;
                      extractCoordButton.disabled = false;
                  });
              });
          }
      });
      videoFile.disabled = false;
  }

  function interactify(dom, onChange) {
      let bbox = $(dom);
      bbox.addClass('bbox');

      let createHandleDiv = (className) => {
          let handle = document.createElement('div');
          handle.className = className;
          bbox.append(handle);
          return handle;
      };

      bbox.resizable({
          containment: 'parent',
          handles: {
              n: createHandleDiv('ui-resizable-handle ui-resizable-n'),
              s: createHandleDiv('ui-resizable-handle ui-resizable-s'),
              e: createHandleDiv('ui-resizable-handle ui-resizable-e'),
              w: createHandleDiv('ui-resizable-handle ui-resizable-w')
          },
          stop: (e, ui) => {
              let position = bbox.position();
              onChange(Math.round(position.left), Math.round(position.top), Math.round(bbox.width()), Math.round(bbox.height()));
          }
      });

      bbox.draggable({
          containment: 'parent',
          handle: createHandleDiv('handle center-drag'),
          stop: (e, ui) => {
              let position = bbox.position();
              onChange(Math.round(position.left), Math.round(position.top), Math.round(bbox.width()), Math.round(bbox.height()));
          }
      });
  }

  let mouse = {
      x: 0,
      y: 0,
      startX: 0,
      startY: 0
  };

  let tmpAnnotatedObject = null;

  doodle.onmousemove = function(e) {
      let ev = e || window.event;
      if (ev.pageX) {
          mouse.x = ev.pageX;
          mouse.y = ev.pageY;
      } else if (ev.clientX) {
          mouse.x = ev.clientX;
          mouse.y = ev.clientY;
      }
      mouse.x -= doodle.offsetLeft;
      mouse.y -= doodle.offsetTop;

      if (tmpAnnotatedObject !== null) {
          tmpAnnotatedObject.width = Math.abs(mouse.x - mouse.startX);
          tmpAnnotatedObject.height = Math.abs(mouse.y - mouse.startY);
          tmpAnnotatedObject.x = (mouse.x - mouse.startX < 0) ? mouse.x : mouse.startX;
          tmpAnnotatedObject.y = (mouse.y - mouse.startY < 0) ? mouse.y : mouse.startY;

          tmpAnnotatedObject.dom.style.width = tmpAnnotatedObject.width + 'px';
          tmpAnnotatedObject.dom.style.height = tmpAnnotatedObject.height + 'px';
          tmpAnnotatedObject.dom.style.left = tmpAnnotatedObject.x + 'px';
          tmpAnnotatedObject.dom.style.top = tmpAnnotatedObject.y + 'px';
      }
  }

  doodle.onclick = function(e) {

      if (doodle.style.cursor != 'crosshair' && maxBBox == 0) {
          return;
      }

      if (tmpAnnotatedObject != null) {
          let annotatedObject = new AnnotatedObject();
          annotatedObject.dom = tmpAnnotatedObject.dom;
          let bbox = new BoundingBox(tmpAnnotatedObject.x, tmpAnnotatedObject.y, tmpAnnotatedObject.width, tmpAnnotatedObject.height);
          annotatedObject.add(new AnnotatedFrame(player.currentFrame, bbox, true));
          annotatedObjectsTracker.annotatedObjects.push(annotatedObject);
          tmpAnnotatedObject = null;

          interactify(
              annotatedObject.dom,
              (x, y, width, height) => {
                  let bbox = new BoundingBox(x, y, width, height);
                  annotatedObject.add(new AnnotatedFrame(player.currentFrame, bbox, true));
              }
          );

          addAnnotatedObjectControls(annotatedObject);

          doodle.style.cursor = 'default';
          selectButton.value = 'Seleccionar'
      } else if (maxBBox == 0) {
          mouse.startX = mouse.x;
          mouse.startY = mouse.y;

          let dom = newBboxElement();
          dom.style.left = mouse.x + 'px';
          dom.style.top = mouse.y + 'px';
          tmpAnnotatedObject = { dom: dom };

      }

      maxBBox = 1;
  }

  function newBboxElement() {
      let dom = document.createElement('div');
      dom.className = 'bbox';
      doodle.appendChild(dom);
      return dom;
  }

  function addAnnotatedObjectControls(annotatedObject) {
      let name = $('<input type="text" value="Name?" />');
      if (annotatedObject.name) {
          name.val(annotatedObject.name);
      }
      name.on('change keyup paste mouseup', function() {
          annotatedObject.name = this.value;
      });

      let id = $('<input type="text" value="ID?" />');
      if (annotatedObject.id) {
          id.val(annotatedObject.id);
      }
      id.on('change keyup paste mouseup', function() {
          annotatedObject.id = this.value;
      });

      let visibleLabel = $('<label>');
      let visible = $('<input type="checkbox" checked="checked" />');
      annotatedObject.visible = visible;
      visible.change(function() {
          let bbox;
          if (this.checked) {
              annotatedObject.dom.style.display = 'block';
              let jquery = $(annotatedObject.dom);
              let position = jquery.position();
              bbox = new BoundingBox(Math.round(position.left), Math.round(position.top), Math.round(jquery.width()), Math.round(jquery.height()));
          } else {
              annotatedObject.dom.style.display = 'none';
              bbox = null;
          }
          annotatedObject.add(new AnnotatedFrame(player.currentFrame, bbox, true));
      });
      visibleLabel.append(visible);
      visibleLabel.append('Is visible?');

      let hideLabel = $('<label>');
      let hide = $('<input type="checkbox" />');
      hide.change(function() {
          annotatedObject.hideOthers = this.checked;
      });
      hideLabel.append(hide);
      hideLabel.append('Hide others?');

      let del = $('<input type="button" value="Borrar Bounding Box" />');
      del.click(function() {
          for (let i = 0; annotatedObjectsTracker.annotatedObjects.length; i++) {
              if (annotatedObject === annotatedObjectsTracker.annotatedObjects[i]) {
                  clearAnnotatedObject(i);
                  break;
              }
          }

          maxBBox = 0;
          document.getElementById('frm:accordion:posX').value = null;
          document.getElementById('frm:accordion:posY').value = null;
          document.getElementById('frm:accordion:posX2').value = null;
          document.getElementById('frm:accordion:posY2').value = null;
      });

      let div = $('<div></div>');
      div.css({
          'border': '1px solid black',
          'display': 'inline-block',
          'margin': '5px',
          'padding': '10px'
      });
      //div.append(name);
      //div.append($('<br />'));
      //div.append(id);
      //div.append($('<br />'));
      //div.append(visibleLabel);
      //div.append($('<br />'));
      //div.append(hideLabel);
      //div.append($('<br />'));
      div.append(del);

      annotatedObject.controls = div;

      $('#objects').append(div);
  }

  function extractCoord() {

      let totalFrames = framesManager.frames.totalFrames();
      for (let i = 0; i < annotatedObjectsTracker.annotatedObjects.length; i++) {
          let annotatedObject = annotatedObjectsTracker.annotatedObjects[i];


          for (let frameNumber = 0; frameNumber < totalFrames; frameNumber++) {
              let annotatedFrame = annotatedObject.get(frameNumber);
              let bbox = annotatedFrame.bbox;
              if (bbox != null) {

                  document.getElementById('frm:accordion:posX').value = bbox.x;
                  document.getElementById('frm:accordion:posY').value = bbox.y;
                  document.getElementById('frm:accordion:posX2').value = bbox.x + bbox.width;
                  document.getElementById('frm:accordion:posY2').value = bbox.y + bbox.height;
                  break;
              }
          }

      }
  }

  // Keyboard shortcuts
  window.onkeydown = function(e) {
      let preventDefault = true;

      if (e.keyCode === 32) { // space
          player.toogle();
      } else if (e.keyCode === 78 && maxBBox == 0) { // n
          doodle.style.cursor = 'crosshair';
      } else if (e.keyCode === 27) { // escape
          if (tmpAnnotatedObject != null) {
              doodle.removeChild(tmpAnnotatedObject.dom);
              tmpAnnotatedObject = null;
          }
          doodle.style.cursor = 'default';
          selectButton.value = 'Seleccionar'
      } else if (e.keyCode == 37) { // left
          player.seek(player.currentFrame - 1);
      } else if (e.keyCode == 39) { // right
          player.seek(player.currentFrame + 1);
      } else {
          preventDefault = false;
      }

      if (preventDefault) {
          e.preventDefault();
      }
  };