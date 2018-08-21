(function(w) {
	var images=[
		{ name: "http://115.236.99.75/10k.jpg"},
		{ name: "http://att2.citysbs.com/10k.jpg"},
		{ name: "http://att3.citysbs.com/10k.jpg"}
	];

	images.end = images.length;
	images.start = 0;

	var __TEST_IDC__={
		result:[],
		init:function(){
			images.start = 0;
			this.load_img();
		},
		load_img:function()
		{
			if(images.start>=images.end  || typeof (images[images.start]) =="undefined" )
			{
				__TEST_IDC__.upload();
                                return;
			}

			var url =  images[images.start].name
				+ '?t=' + (new Date().getTime()) + Math.random(),
				img = new Image();

		
			img.onload=function() {
				tend=new Date().getTime();
				__TEST_IDC__.result.push(tend-tstart);
				img.onload=img.onerror=null;
				img=null;
				images.start++;
				__TEST_IDC__.load_img();

			};
			img.onerror=function() {
				__TEST_IDC__.result.push(99999);
				img.onload=img.onerror=null;
				img=null;
				images.start++;
				__TEST_IDC__.load_img();
			};
			tstart = new Date().getTime();
			img.src=url;
		},
		upload:function()
		{
			var uploadurl="http://dm.19lou.com/idctest.gif?t="+(new Date().getTime());
			for(i=0; i<images.end; i++) {
				uploadurl+="&idc"+(i+1)+"="+__TEST_IDC__.result[i];
			}

			(new Image()).src=uploadurl;

		}
	};

	if(parseInt(Math.random()*100%2) ==0)
	{
		__TEST_IDC__.init();
	}


}(window));
