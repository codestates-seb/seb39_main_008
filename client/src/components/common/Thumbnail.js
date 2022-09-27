import styled from 'styled-components';

const ThumbnailContainer = styled.div`
  height: ${(props) => props.height};
  border-radius: ${(props) => props.borderRadius};
  background-image: url(${(props) => props.imageURL});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center center;
`;

const Thumbnail = ({ height, borderRadius, imageURL }) => {
  return (
    <ThumbnailContainer
      height={height}
      borderRadius={borderRadius}
      imageURL={imageURL}
    ></ThumbnailContainer>
  );
};

export default Thumbnail;
