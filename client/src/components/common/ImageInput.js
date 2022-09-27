import { useState, useRef } from 'react';
import styled from 'styled-components';
const AddImgButton = styled.div``;
const ImageInput = ({ onFileChange }) => {
  const imgRef = useRef();
  const [preview, setPreview] = useState(null);

  //todo api 나오고 지우기
  const upLoadImg = (file) => {
    return new Promise((res, rej) => {
      if (file) {
        const imgURL =
          'https://cdn.pixabay.com/photo/2022/06/15/05/31/sparrow-7263192_960_720.jpg';
        res(imgURL);
      }
      rej(new Error('upLoadImg err'));
    });
  };

  const onButtonClick = (event) => {
    event.preventDefault();
    imgRef.current.click();
  };

  const handleImageUpload = async () => {
    // 이미지 파일 전송 후 url 받아오기
    const imageFile = imgRef.current.files[0];
    const imgURL = await upLoadImg(imageFile);
    onFileChange(imgURL);
    setPreview(imgURL);
  };

  return (
    <>
      <AddImgButton className="addImgButton" onClick={onButtonClick}>
        {preview ? (
          <img src={preview} alt="preview IMG" />
        ) : (
          <p>
            사진을
            <br />
            선택해
            <br />
            주세요
          </p>
        )}
      </AddImgButton>
      <input
        className="image"
        accept="image/*"
        type="file"
        name="Img"
        id="Img"
        ref={imgRef}
        onChange={handleImageUpload}
      />
    </>
  );
};

export default ImageInput;
