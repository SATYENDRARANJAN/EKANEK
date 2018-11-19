package docsapp.com.ekanek.view;

import docsapp.com.ekanek.dto.FlickrImageResponseDTO;

public interface FlickrImageView {
        void onResponseSuccess(FlickrImageResponseDTO flickrImageResponseDTO);
        void onResponseFailure(String message);
}
