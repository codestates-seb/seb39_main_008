import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import TextEditor from '../components/TextEditor';
import BorderButton from '../components/common/BorderButton';
import ImageInput from '../components/common/ImageInput';
import ConfirmModal from './common/ConfirmModal';
import styled from 'styled-components';
import {
  borderRadius,
  colors,
  fontSize,
  layout,
  space,
} from '../assets/styles/theme';
import { getBookList } from '../lib/axios';

export const Container = styled.form`
  padding: 0 ${space.spaceL};
  margin: 0 auto;
  width: 100%;
  /* max-width: 710px; */
`;

export const Mid = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 0 ${space.spaceS};
  border-bottom: 1px solid ${colors.grey};
  & > div {
    width: 50%;
  }

  & input {
    padding: 0;
    margin-top: ${space.spaceM};
    overflow: visible;
    width: 100%;
    height: 100%;
    border: none;
    display: inline-block;
  }
  & select {
    width: 30%;
    border: none;
    background-color: ${colors.dimGrey};
    border-radius: ${borderRadius.borderRadiusS};
  }
  & input:focus-visible {
    outline: none;
  }
  .title {
    height: 50px;
    font-size: ${fontSize.fontSizeLL};
  }
  .title::placeholder {
    font-size: ${fontSize.fontSizeLL};
  }
  .subtitle {
    height: 50px;
    font-size: ${fontSize.fontSizeL};
  }
  .subtitle::placeholder {
    font-size: ${fontSize.fontSizeL};
  }
  label {
    margin-top: ${space.spaceM};
    font-size: ${fontSize.fontSizeS};
    width: 40px;
    display: inline-block;
  }
  .radioBox {
    margin-top: ${space.spaceM};
    font-size: ${fontSize.fontSizeS};
    p {
      margin-bottom: ${space.spaceS};
    }
    input,
    label {
      cursor: pointer;
      margin: calc(${space.spaceS} / 3);
      display: inline;
    }
    input {
      width: 10px;
      display: inline;
    }
  }
  .addImgButton {
    cursor: pointer;
    margin: ${space.spaceM} 0 ${space.spaceM} ${space.spaceS};
    width: 50%;
    height: 427.5px;
    overflow: hidden;
    border-radius: ${borderRadius.borderRadiusM};
    border: 1px dashed ${colors.dimGrey};
    ${layout.flexCenter}
    & > p {
      font-size: ${fontSize.fontSizeL};
      color: ${colors.text4};
    }
    &:hover {
      & > p {
        color: ${colors.text3};
      }
    }
  }
  .image {
    display: none;
  }
`;
export const Bottom = styled.div`
  width: 100%;
  padding-bottom: ${space.spaceL};
  .preview {
    p {
      font-size: ${fontSize.fontSizeM};
      padding: ${space.spaceS} ${space.spaceS} 0 ${space.spaceS};
    }
  }
  .previewImg {
    padding: ${space.spaceS};
    width: 100%;
  }
  .quill {
    z-index: 2000;
    margin: ${space.spaceM} ${space.spaceS};
    color: ${colors.text2};
    min-height: 20rem;
    font-size: ${fontSize.fontSizeM};
  }
  .ql-editor {
    padding: 0px;
  }

  .rightbox {
    & > * {
      margin-right: ${space.spaceS};
    }
    display: flex;
    justify-content: end;
    align-items: flex-end;
    margin: ${space.spaceM} ${space.spaceS};
  }
  .Error {
    font-size: calc(${fontSize.fontSizeS}*1.2);
    color: ${colors.red};
    display: inline-block;
  }
`;

const WriteOrEditDiaryForm = (props) => {
  const [fileURL, setFileURL] = useState(null);
  const [booklist, setBooklist] = useState([]);
  const [confirm, setConfirm] = useState(false);
  const navigate = useNavigate();
  const {
    register,
    setValue,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm({});

  const onFileChange = (imgURL) => {
    setFileURL(imgURL);
    console.log(fileURL);
    // setPreview(imgURL);
  };

  useEffect(() => {
    register('content', {
      required: true,
      validate: (editorContent) => {
        if (editorContent.replace(/<[^>]*>?/g, '').trim() === '') return false;
        if (editorContent === '<p><br></p>') return false;
      },
    });
  }, [register]);

  useEffect(() => {
    if (props.isEdit) {
      setValue('content', props.data.content);
    }
  }, []);

  useEffect(async () => {
    const data = await getBookList(19);
    setBooklist(data.data);
    console.log(booklist);
  }, []);

  const onChange = (editorContent) => {
    setValue('content', editorContent);
  };

  const onUpdateDiary = (data) => {
    console.log('update', data);
  };

  const onWriteDiary = (data) => {
    console.log('write', data);
  };

  const editorContent = watch('content');

  const onCancle = () => {
    setConfirm(!confirm);
  };

  const confirmCancle = (res) => {
    if (res) {
      onCancle();
      navigate(-1);
    } else {
      onCancle();
    }
  };
  return (
    <Container
      onSubmit={
        props.isEdit ? handleSubmit(onUpdateDiary) : handleSubmit(onWriteDiary)
      }
    >
      <Mid>
        <div>
          <input
            wrap="on"
            className="title"
            type="text"
            placeholder="제목"
            defaultValue={props.data ? props.data.title : null}
            {...register('title', {
              required: true,
              validate: (value) => {
                if (value.trim() === '') return false;
              },
            })}
          />

          <input
            wrap="on"
            className="subtitle"
            id="subtitle"
            type="text"
            placeholder="소제목"
            defaultValue={props.data ? props.data.subtitle : null}
            {...register('subtitle', {
              required: true,
              validate: (value) => {
                if (value.trim() === '') return false;
              },
            })}
          />

          <br />
          <label className="book" htmlFor="book">
            일기장
          </label>
          <select id="book" {...register('book', { required: true })}>
            {booklist &&
              booklist.map((el, idx) => (
                <option value={el.title} key={idx}>
                  {el.title}
                </option>
              ))}
          </select>
          <br />
          <label className="category" htmlFor="category">
            주제
          </label>
          <select id="category" {...register('category', { required: true })}>
            {['일상 공유', '공감과 치유', '문화 생활', '여행 기록', '자유'].map(
              (el, idx) => (
                <option value={el} key={idx}>
                  {el}
                </option>
              )
            )}
          </select>
        </div>
        <ImageInput onFileChange={onFileChange} />
      </Mid>
      <Bottom>
        {props.isEdit && (
          <img
            className="previewImg"
            src={fileURL || props.data.diaryimage}
            alt="preview Img"
          />
        )}
        <TextEditor content={editorContent} onChange={onChange} />
        <div className="rightbox">
          <p className="Error">{errors && '모든 내용을 입력해 주세요'}</p>
          <BorderButton
            width="120px"
            height="40px"
            type="submit"
            fontSize={fontSize.fontSizeM}
            text={props.isEdit ? '수정하기' : '기록하기'}
          />
          <BorderButton
            text="취소"
            width="120px"
            height="40px"
            fontSize={fontSize.fontSizeM}
            onClick={() => {
              setConfirm(!confirm);
            }}
          />
        </div>
      </Bottom>
      {confirm && (
        <ConfirmModal
          message={'취소하시겠습니까?'}
          onComfirm={confirmCancle}
          target={'작성한 내용은 복구할 수 없습니다'}
        />
      )}
    </Container>
  );
};

export default WriteOrEditDiaryForm;
